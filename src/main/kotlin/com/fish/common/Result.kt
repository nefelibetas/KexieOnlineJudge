package com.fish.common

import com.fasterxml.jackson.annotation.JsonInclude
import com.fish.exception.ServiceException
import com.fish.exception.ServiceExceptionEnum
import java.io.Serializable

@JsonInclude(JsonInclude.Include.NON_NULL)
data class Result<T>(val code: Int,val message: String, val data: T?) : Serializable {
    constructor(exception: ServiceException) : this(exception.code, exception.msg, null)

    constructor(exceptionEnum: ServiceExceptionEnum) : this(exceptionEnum.code, exceptionEnum.msg, null)

    constructor(code: Int, message: String) : this(code, message, null)

    constructor(data: T) : this(200, "success", data)
}
