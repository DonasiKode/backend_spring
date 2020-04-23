package com.donasikode.server.donasikode.initializer

import com.donasikode.server.donasikode.exceptions.ClientKeyException
import com.donasikode.server.donasikode.repositories.ClientRepository
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


class ClientsAuthInterceptor(var repository: ClientRepository): HandlerInterceptorAdapter() {

    override fun preHandle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any
    ): Boolean {
//        val token = request.getHeader("api_key")
//        token?.let {
//            repository.findByAccessKey(token).firstOrNull()?.let {
//                return true
//            }
//        }
//        throw ClientKeyException()
        return true
    }
}