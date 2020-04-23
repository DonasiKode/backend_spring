package com.donasikode.server.donasikode.controllers

import com.donasikode.server.donasikode.exceptions.*
import com.donasikode.server.donasikode.models.ServerResponse
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus


@ControllerAdvice
class ExceptionsController {

    @ResponseStatus(
        value = HttpStatus.FORBIDDEN
    )
    @ExceptionHandler(ClientKeyException::class)
    @ResponseBody
    fun clientNotAuthorized(e: ServerException): ServerResponse<Any> {
        return ServerResponse(
            message = e.message,
            code = e.code,
            data = null
        )
    }

    @ResponseStatus(
        value = HttpStatus.FORBIDDEN
    )
    @ExceptionHandler(InvalidAuthException::class)
    @ResponseBody
    fun invalidAuthException(e: ServerException): ServerResponse<Any> {
        return ServerResponse(
            message = e.message,
            code = e.code,
            data = null
        )
    }

    @ResponseStatus(
        value = HttpStatus.FORBIDDEN
    )
    @ExceptionHandler(InvalidUsernameOrEmailException::class)
    @ResponseBody
    fun invalidUsernameOrEmailException(e: ServerException): ServerResponse<Any> {
        return ServerResponse(
            message = e.message,
            code = e.code,
            data = null
        )
    }

    @ResponseStatus(
        value = HttpStatus.CONFLICT
    )
    @ExceptionHandler(UserAlreadyExistException::class)
    @ResponseBody
    fun userAlreadyExistException(e: ServerException): ServerResponse<Any> {
        return ServerResponse(
            message = e.message,
            code = e.code,
            data = null
        )
    }

    @ResponseStatus(
        value = HttpStatus.NOT_ACCEPTABLE
    )
    @ExceptionHandler(InvalidRequestException::class)
    @ResponseBody
    fun invalidRequestException(e: ServerException): ServerResponse<Any> {
        return ServerResponse(
            message = e.message,
            code = e.code,
            data = null
        )
    }

}