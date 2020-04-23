package com.donasikode.server.donasikode.models

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
open class ServerResponse<T>(
    var data: T? = null,
    var message: String? = "Success",
    var code: Int = 200,
    var meta: Map<String, Any>? = mapOf()
)