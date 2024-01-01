package com.fish.keXieOpenJudge.exception
/**
 * 服务异常类, 数据基本使用枚举填充
 */
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
