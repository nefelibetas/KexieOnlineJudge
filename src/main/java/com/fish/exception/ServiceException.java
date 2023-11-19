package com.fish.exception;

import lombok.Getter;

@Getter
public class ServiceException extends RuntimeException{
    private final String msg;
    private final int code;
    public ServiceException(ServiceExceptionEnum serviceExceptionEnum) {
        this.msg= serviceExceptionEnum.getMsg();
        this.code= serviceExceptionEnum.getCode();
    }
    public ServiceException(int code, String message) {
        this.msg= message;
        this.code= code;
    }
}
