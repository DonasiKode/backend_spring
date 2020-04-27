package com.donasikode.server.donasikode.initializer

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTCreator
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.impl.JWTParser
import com.auth0.jwt.interfaces.DecodedJWT
import com.donasikode.server.donasikode.models.User
import com.donasikode.server.donasikode.repositories.UserRepository

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component
import java.util.*
import java.util.concurrent.TimeUnit


@Component
object JwtTokenUtil {
    @Value("\${server.secret}")
    private var serverSecret: String = "==D0n4s1K0d3=="
    // expired after 7 day
    private val expireTime = TimeUnit.DAYS.toMillis(7)

    private val algorithm: Algorithm = Algorithm.HMAC256(serverSecret)
    private val generator = { user: User, alg: Algorithm ->
        JWT.create()
            .withIssuer("auth0")
            .withClaim("username", user.username)
            .withClaim("id", user.id)
            .withExpiresAt(Date(System.currentTimeMillis() + expireTime))
            .sign(alg)
    }

    private val verifier: JWTVerifier = JWT.require(algorithm).withIssuer("auth0").build()

    fun generateToken(userInfo: User): String = generator(userInfo, algorithm)

    fun validateToken(token: String): Pair<Long, String> {
        val decoded: DecodedJWT = verifier.verify(token)
        return decoded.getClaim("id").asLong() to decoded.getClaim("username").asString()
    }
}