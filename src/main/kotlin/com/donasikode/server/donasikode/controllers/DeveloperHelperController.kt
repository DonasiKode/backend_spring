package com.donasikode.server.donasikode.controllers

import com.donasikode.server.donasikode.models.Clients
import com.donasikode.server.donasikode.repositories.ClientRepository
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@Tag(name = "Developer Helper", description = "Developer helper API")
class DeveloperHelperController(
    val repository: ClientRepository
) {
    @GetMapping("/clients")
    fun getAllClients(): List<Clients> {
        return repository.findAll()
    }
}