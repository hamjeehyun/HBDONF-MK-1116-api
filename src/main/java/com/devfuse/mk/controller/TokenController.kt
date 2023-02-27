package com.devfuse.mk.controller

import com.devfuse.mk.domain.dto.TokenDto
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import org.json.simple.JSONObject
import org.json.simple.parser.JSONParser
import org.json.simple.parser.ParseException
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate

@RestController
@RequestMapping("mk/token")
class TokenController {
    @Value("\${firebase.key}")
    private val FIREBASE_KEY: String? = null


    @GetMapping("{uid}")
    @Throws(FirebaseAuthException::class, ParseException::class)
    fun getFirebaseUid(@PathVariable(name = "uid") uid: String?): TokenDto {
        val url =
            "https://identitytoolkit.googleapis.com/v1/accounts:signInWithCustomToken?key=$FIREBASE_KEY"
        val token = FirebaseAuth.getInstance().createCustomToken(uid)
        val httpHeaders = HttpHeaders()

        httpHeaders.contentType = MediaType.APPLICATION_JSON

        val body: MutableMap<String, Any> = HashMap()

        body["token"] = token
        body["returnSecureToken"] = true

        val entity: HttpEntity<*> = HttpEntity<Map<String, Any>>(body, httpHeaders)
        val restTemplate = RestTemplate()
        val response = restTemplate.exchange(
            url, HttpMethod.POST, entity,
            String::class.java
        )

        val parser = JSONParser()
        val obj = parser.parse(response.body)
        val jsonObj = obj as JSONObject
        val tokenDto = TokenDto()

        tokenDto.idToken = jsonObj["idToken"] as String?

        return tokenDto
    }
}