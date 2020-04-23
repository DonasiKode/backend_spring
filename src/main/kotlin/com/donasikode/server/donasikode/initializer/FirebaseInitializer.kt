package com.donasikode.server.donasikode.initializer

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Primary
import org.springframework.core.io.ClassPathResource
import org.springframework.core.io.Resource
import org.springframework.stereotype.Component
import java.io.FileInputStream
import java.io.IOException


@Component
class FirebaseInitializer {

    @Primary
    @Bean
    @Throws(IOException::class)
    fun firebaseInit() {
        val resource: Resource = ClassPathResource("donasikode-firebase-adminsdk.json")
        val input = resource.getInputStream()

        val options = FirebaseOptions.Builder()
            .setCredentials(GoogleCredentials.fromStream(input))
            .setDatabaseUrl("https://donasikode.firebaseio.com")
            .build()

        FirebaseApp.initializeApp(options)
    }
}