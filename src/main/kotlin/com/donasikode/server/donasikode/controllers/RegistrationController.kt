package com.donasikode.server.donasikode.controllers

import com.donasikode.server.donasikode.exceptions.InvalidAuthException
import com.donasikode.server.donasikode.exceptions.InvalidUsernameOrEmailException
import com.donasikode.server.donasikode.exceptions.UserAlreadyExistException
import com.donasikode.server.donasikode.initializer.Crypton
import com.donasikode.server.donasikode.models.AuthResponse
import com.donasikode.server.donasikode.models.ServerResponse
import com.donasikode.server.donasikode.models.User
import com.donasikode.server.donasikode.repositories.ClientRepository
import com.donasikode.server.donasikode.repositories.UserRepository
import com.donasikode.server.donasikode.repositories.onVerified
import com.google.gson.Gson
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/auth")
class RegistrationController(
    var clients: ClientRepository,
    var userRepository: UserRepository
) {

    val crypto = Crypton()

    @PostMapping("/firebase")
    fun firebaseLogin(
        @RequestBody data: AuthBody,
        @RequestHeader("app_key") clientKey: String
    ): ServerResponse<User> {
        return clients.onVerified(clientKey) {
            return@onVerified ServerResponse(
                data = User(0, "dummy","","","","")
            )
        }
    }

    @PostMapping("/register")
    fun registration(
        @RequestBody data: AuthBody,
        @RequestHeader("app_key") clientKey: String
    ): ServerResponse<AuthResponse> {
        val str = crypto.decrypt(data.data, clientKey)
        val r = Gson().fromJson(str, RegistrationData::class.java)
        val user = userRepository.findByUsernameOrEmail(r.username, r.email)
        if (user != null) {
            throw UserAlreadyExistException(
                r.username.takeIf { it == user.username },
                r.email.takeIf { it == user.email }
            )
        }
        val saved = userRepository.save(
            User(
                0,
                r.username,
                r.password,
                r.fullname,
                r.email,
                r.phone,
                r.role ?: "user",
                System.currentTimeMillis(),
                System.currentTimeMillis(),
                true
            )
        )
        return ServerResponse(data = AuthResponse.fromUser(saved))
    }

    @PostMapping("/login")
    fun login(
        @RequestBody data: AuthBody,
        @RequestHeader("app_key") clientKey: String
    ): ServerResponse<AuthResponse> {
        val str = crypto.decrypt(data.data, clientKey)
        val r = Gson().fromJson(str, LoginData::class.java)
        val user = userRepository.findByUsernameOrEmail(r.username ?: "", r.email ?: "")
        if (user != null) {
            if (user.password != r.password) {
                throw InvalidAuthException()
            }
            return ServerResponse(AuthResponse.fromUser(user))
        }
        throw InvalidUsernameOrEmailException(
            r.username,
            r.email
        )

    }
}

data class AuthBody(
    var data: String
)

data class RegistrationData(
    var username: String,
    var password: String,
    var email: String,
    var fullname: String,
    var phone: String?,
    var role: String?
)

data class LoginData(
    var username: String? = null,
    var email: String? = null,
    var password: String? = null
)