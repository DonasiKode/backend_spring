package com.donasikode.server.donasikode.models

import com.donasikode.server.donasikode.initializer.JwtTokenUtil

data class AuthResponse(
    val id: Long,
    val username: String,
    val email: String,
    var token: String,
    val role: String?
) {
    companion object {
        fun fromUser(user: User): AuthResponse {
            val token = JwtTokenUtil.generateToken(user)
            return AuthResponse(
                user.id,
                user.username,
                user.email,
                token,
                user.role
            )
        }
    }
}