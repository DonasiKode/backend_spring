package com.donasikode.server.donasikode.initializer

import com.donasikode.server.donasikode.exceptions.InvalidRequestException
import org.springframework.stereotype.Component
import java.util.*

@Component
class Crypton {

    fun encrypt(data: String, key: String): String {
        val base64data = Base64.getEncoder().encode(data.toByteArray())
        return String(base64data).reversed()
    }

    fun decrypt(base64Data: String, key: String): String {
        try {
            val data = Base64.getDecoder().decode(base64Data.reversed())
            return String(data)
        } catch (e: Exception) {
            throw InvalidRequestException()
        }
    }

}