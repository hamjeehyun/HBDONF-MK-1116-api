package com.devfuse.mk.filter

import com.devfuse.mk.domain.dto.user.UserResDto
import com.devfuse.mk.service.UserService
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseToken
import org.apache.http.HttpStatus
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter
import java.io.IOException
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JwtFilter(userService: UserService, firebaseAuth: FirebaseAuth) : OncePerRequestFilter() {
    private val userService: UserService
    private val firebaseAuth: FirebaseAuth

    init {
        this.userService = userService
        this.firebaseAuth = firebaseAuth
    }

    @Throws(ServletException::class, IOException::class)
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {

        // get the token from the request
        val decodedToken: FirebaseToken
        val token = request.getHeader("Authorization")
        if (token == null) {
            setUnauthorizedResponse(response, "INVALID_HEADER")
            return
        }

        // verify IdToken
        decodedToken = try {
            firebaseAuth.verifyIdToken(token)
        } catch (e: FirebaseAuthException) {
            setUnauthorizedResponse(response, "INVALID_TOKEN")
            return
        }

        // User를 가져와 SecurityContext에 저장한다.
        try {
            val user: UserResDto = userService.findUserByUid(decodedToken.uid)
            val authentication = UsernamePasswordAuthenticationToken(
                user, null, null
            )
            SecurityContextHolder.getContext().authentication = authentication
        } catch (e: NoSuchElementException) {
            setUnauthorizedResponse(response, "USER_NOT_FOUND")
            return
        }
        filterChain.doFilter(request, response)
    }

    @Throws(IOException::class)
    private fun setUnauthorizedResponse(response: HttpServletResponse, code: String) {
        response.status = HttpStatus.SC_UNAUTHORIZED
        response.contentType = "application/json"
        response.writer.write("{\"code\":\"$code\"}")
    }
}