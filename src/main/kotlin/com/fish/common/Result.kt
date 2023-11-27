package com.fish.common

import com.fasterxml.jackson.annotation.JsonInclude
import com.fish.exception.ServiceException
import com.fish.exception.ServiceExceptionEnum
import java.io.Serializable

@JsonInclude(JsonInclude.Include.NON_NULL)
class Result<T> : Serializable {
    private val code: Int
    val message: String
    val data: T?

    constructor(exception: ServiceException) {
        data = null
        this.code = exception.code
        message = exception.msg
    }

    constructor(exceptionEnum: ServiceExceptionEnum) {
        data = null
        this.code = exceptionEnum.code
        message = exceptionEnum.msg
    }

    constructor(code: Int, message: String) {
        data = null
        this.code = code
        this.message = message
    }

    constructor(code: Int, message: String, data: T) {
        this.code = code
        this.message = message
        this.data = data
    }

    constructor(data: T) {
        this.data = data
        this.code = 200
        message = "success"
    }
}
