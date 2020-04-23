package com.donasikode.server.donasikode.models

import lombok.Data
import org.springframework.data.jpa.repository.JpaRepository
import javax.persistence.Entity
import javax.persistence.Id

@Data
@Entity
class UserRole(
    @Id var id: Long,
    var rolename: String,
    var createTime: Long
)

interface UserRoleRepository: JpaRepository<UserRole, Long> {
    fun findByRolenameIgnoreCase(rolename: String): UserRole?
}