package com.fish.exception
import lombok.Getter
/**
 * 可能出现的服务异常
 */
@Getter
enum class ServiceExceptionEnum(val code: Int, val msg: String) {
    AUTHENTICATION_FAILURE(-200, "认证失败"),
    INSUFFICIENT_PERMISSIONS(-201, "权限不足"),
    ACCOUNT_NOT_FOUND(-202, "用户不存在"),
    EMAIL_NO_PASSWORD_WRONG(-203, "邮箱或密码错误"),
    ACCOUNT_EXISTED(-204, "用户已经存在"),
    METHOD_ARGUMENT_NOT_VALID(-205, ""),
    OPERATE_ERROR(-206, "操作失败"),
    KEY_ARGUMENT_NOT_INPUT(-207, "关键信息未输入"),
    TOKEN_ERROR(-208, "Token异常"),
    NOT_FOUND(-209, "未找到目标"),
    UN_LOGIN(-210, "还未登录或Token过期"),
    SELECT_NOT_IN(-212, "该选项不存在"),
    METHOD_NOT_SUPPORT(-2000, "方法不支持"),
    UNKNOWN_ERROR(-2001, "未知异常"),
    NULL_POINTER(-2004, "出现空指针异常,请通知管理员排查")
}
