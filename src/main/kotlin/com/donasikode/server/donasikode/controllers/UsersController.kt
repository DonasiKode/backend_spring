package com.donasikode.server.donasikode.controllers

import com.donasikode.server.donasikode.models.ServerResponse
import com.donasikode.server.donasikode.models.User
import com.donasikode.server.donasikode.models.UserRoleRepository
import com.donasikode.server.donasikode.repositories.ClientRepository
import com.donasikode.server.donasikode.repositories.UserRepository
import com.donasikode.server.donasikode.repositories.verify
import com.donasikode.server.donasikode.statics.HeaderParam
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/users")
@Tag(name = "Users", description = "Users API")
class UsersController(
    val userRepo: UserRepository,
    val clientRepo: ClientRepository,
    val roleRepo: UserRoleRepository
) {

    @GetMapping
    fun getAll(
        @RequestParam(value = "query") query: String?,
        @RequestHeader(HeaderParam.CLIENT_KEY, required = true) clientKey: String,
        @RequestHeader(HeaderParam.AUTH_KEY, required = true) userToken: String
    ): ServerResponse<List<User>> {
        clientRepo.verify(clientKey)


        val listUsers = if (query.isNullOrBlank()) {
            userRepo.findAll()
        } else {
            userRepo.findByFullnameContaining(query)
        }
        return ServerResponse(data = listUsers)
    }

    @PostMapping("/new")
    fun register(
        @RequestBody user: User,
        @RequestHeader(HeaderParam.CLIENT_KEY, required = true) clientKey: String,
        @RequestHeader(HeaderParam.AUTH_KEY, required = true) userToken: String
    ): ServerResponse<User> {
        clientRepo.verify(clientKey)
        val data = userRepo.save(user.apply {
            addTime = System.currentTimeMillis()
            role = role?.let {
                roleRepo.findByRolenameIgnoreCase(it)?.rolename
            } ?: "user"
        })
        return ServerResponse(data = data)
    }

    @PatchMapping("/edit")
    fun edit(
        @RequestBody user: User,
        @RequestHeader(HeaderParam.CLIENT_KEY, required = true) clientKey: String,
        @RequestHeader(HeaderParam.AUTH_KEY, required = true) userToken: String
    ) {

    }
}