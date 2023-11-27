package com.fish.exception

class ServiceException : RuntimeException {
    val msg: String
    val code: Int
    constructor(serviceExceptionEnum: ServiceExceptionEnum) {
        msg = serviceExceptionEnum.msg
        this.code = serviceExceptionEnum.code
    }
    constructor(code: Int, message: String) {
        msg = message
        this.code = code
    }
}
