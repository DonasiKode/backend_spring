package com.donasikode.server.donasikode.initializer

import com.donasikode.server.donasikode.models.Clients
import com.donasikode.server.donasikode.models.UserRole
import com.donasikode.server.donasikode.models.UserRoleRepository
import com.donasikode.server.donasikode.repositories.ClientRepository
import lombok.extern.slf4j.Slf4j
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@Slf4j
class DatabaseLoader {

    @Bean
    fun initDatabase(
        clients: ClientRepository,
        roles: UserRoleRepository
    ): CommandLineRunner? {
        return CommandLineRunner { args: Array<String?>? ->
//            repository.save(Clients(
//                0,
//                UUID.randomUUID().toString().replace("-",""),
//                "Android Client",
//                ""
//            ))
            // add base user role
            roles.deleteAll()
            roles.save(UserRole(1, "admin", System.currentTimeMillis()))
            roles.save(UserRole(2, "user", System.currentTimeMillis()))
            roles.save(UserRole(3, "amil", System.currentTimeMillis()))
            roles.save(UserRole(4, "donatur", System.currentTimeMillis()))
            clients.deleteAll()
            clients.save(Clients(1, "demo","demo1234", "demo", "demo"))
        }
    }
}