package com.fish.exception;

import lombok.Getter;

@Getter
public enum ServiceExceptionEnum {
    AUTHENTICATION_FAILURE(-200, "认证失败"),
    INSUFFICIENT_PERMISSIONS(-201, "权限不足"),
    ACCOUNT_NOT_FOUND(-202, "用户不存在"),
    USERNAME_NO_PASSWORD_WRONG(-203, "邮箱或密码错误"),
    ACCOUNT_EXISTED(-204, "用户已经存在"),
    METHOD_ARGUMENT_NOT_VALID(-205, ""),
    OPERATE_ERROR(-206, "操作失败"),
    KEY_ARGUMENT_NOT_INPUT(-207, "关键信息未输入完整"),
    TOKEN_ERROR(-208, "Token异常"),
    NOT_FOUND(-209, "未找到目标"),
    UN_LOGIN(-210, "还没有登录"),
    METHOD_NOT_SUPPORT(-2000, "方法不支持"),
    UNKNOWN_ERROR(-2001, "未知异常"),
    MYBATIS_SYSTEM_ERROR(-2002, "数据库出现错误"),
    ILLEGAL_ARGUMENT(-2003, "非法参数"),
    IO_ERROR(-2004, "IO出现错误")
    ;
    private final int code;
    private final String msg;
    ServiceExceptionEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
