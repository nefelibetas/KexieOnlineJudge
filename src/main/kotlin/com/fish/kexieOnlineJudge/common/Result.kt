package com.fish.kexieOnlineJudge.common

import com.fasterxml.jackson.annotation.JsonInclude
import com.fish.kexieOnlineJudge.exception.ServiceException
import com.fish.kexieOnlineJudge.exception.ServiceExceptionEnum
import java.io.Serializable

/**
 * 用于返回响应的封装类, 建议采用 ResultUtil 的方法来实例化返回
 * @param code 响应码
 * @param message 响应信息
 * @param data 响应数据(不一定存在)
 * @see ResultUtil
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
data class Result<T>(val code: Int,val message: String, val data: T?) : Serializable {
    constructor(exception: ServiceException) : this(exception.code, exception.msg, null)

    constructor(exceptionEnum: ServiceExceptionEnum) : this(exceptionEnum.code, exceptionEnum.msg, null)

    constructor(code: Int, message: String) : this(code, message, null)

    constructor(data: T) : this(200, "success", data)
    constructor(): this(200, "success")
}
