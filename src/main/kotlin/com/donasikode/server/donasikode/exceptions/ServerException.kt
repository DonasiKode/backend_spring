package com.donasikode.server.donasikode.exceptions

import org.springframework.http.HttpStatus

open class ServerException(
    override var message: String,
    var code: Int
): RuntimeException() {
}


class UserAlreadyExistException(
    username: String?,
    email: String?
): ServerException("User dengan ${when {
    username.isNullOrBlank().not() -> "username $username"
    email.isNullOrBlank().not() -> "email $email"
    else -> ""
}} sudah ada, silahkan coba lagi.", HttpStatus.CONFLICT.value())



class InvalidUsernameOrEmailException(
    username: String?,
    email: String?
): ServerException("User dengan ${when {
    username.isNullOrBlank().not() -> "username $username"
    email.isNullOrBlank().not() -> "email $email"
    else -> ""
}} tidak ditemukan.", HttpStatus.NOT_FOUND.value())

class InvalidAuthException: ServerException("Password yang anda masukkan salah", HttpStatus.FORBIDDEN.value())
class InvalidRequestException: ServerException("Data yang dikirim tidak dapat di proses.", HttpStatus.NOT_ACCEPTABLE.value())