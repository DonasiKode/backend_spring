package com.donasikode.server.donasikode.initializer

import com.donasikode.server.donasikode.repositories.ClientRepository
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class InterceptorsConfigurator(
    var repository: ClientRepository
): WebMvcConfigurer {
    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(
            ClientsAuthInterceptor(repository)
        ).addPathPatterns("/api/v1/*")
//        super.addInterceptors(registry)
    }
}