package com.devfuse.mk.controller

import com.devfuse.mk.domain.common.CommonResponseDto
import com.devfuse.mk.domain.dto.user.UserReqDto
import com.devfuse.mk.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("mk/user")
class UserController : CommonController() {
    @Autowired
    lateinit var userService: UserService

    @PostMapping("")
    fun registerUser(
        @RequestBody user: UserReqDto
    ): ResponseEntity<CommonResponseDto<String>> {
        return ResponseEntity.ok(
            getResponse(
                userService.registerUser(user)
            )
        )
    }

}