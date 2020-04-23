package com.donasikode.server.donasikode.repositories

import com.donasikode.server.donasikode.exceptions.ClientKeyException
import com.donasikode.server.donasikode.models.Clients
import org.springframework.data.jpa.repository.JpaRepository

interface ClientRepository: JpaRepository<Clients, Long> {
    fun findByAccessKey(accessKey: String): Clients?
}

fun<T> ClientRepository.onVerified(key: String, onVerified: () -> T): T {
    findByAccessKey(key)?.let {
        return onVerified.invoke()
    } ?: throw ClientKeyException()
}

fun ClientRepository.verify(key: String) {
    findByAccessKey(key) ?: throw ClientKeyException()
}