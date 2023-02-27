package com.devfuse.mk.config

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.auth.FirebaseAuth
import lombok.Getter
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.io.FileInputStream

@Getter
@Configuration
class FirebaseConfig {
    @Value("\${firebase.path}")
    private val FIREBASE_PATH: String? = null

    @Bean
    fun getFirebaseAuth(): FirebaseAuth {
        val serviceAccount = FileInputStream(FIREBASE_PATH)

        val options = FirebaseOptions.builder()
            .setCredentials(GoogleCredentials.fromStream(serviceAccount))
            .build()
        FirebaseApp.initializeApp(options)
        return FirebaseAuth.getInstance(FirebaseApp.getInstance())
    }
}