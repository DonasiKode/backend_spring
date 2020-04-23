package com.donasikode.server.donasikode.exceptions

import org.springframework.http.HttpStatus


class ClientKeyException: ServerException(
    "Client Not Authorized, invalid api_key.",
    HttpStatus.FORBIDDEN.value()
)
