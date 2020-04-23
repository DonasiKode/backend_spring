package com.donasikode.server.donasikode.repositories

import com.donasikode.server.donasikode.models.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.lang.Nullable

interface UserRepository: JpaRepository<User, Long> {
    fun findByEmail(email: String): List<User>
    fun findByFullnameIgnoreCase(fullname: String): List<User>
    fun findByFullnameContaining(fullname: String): List<User>
    fun findByUsername(username: String): List<User>
    fun findByRole(role: String): List<User>
    @Nullable fun findByUsernameOrEmail(username: String?, email: String?): User?
}