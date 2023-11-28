package com.fish.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fish.exception.ServiceException;
import com.fish.exception.ServiceExceptionEnum;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude(Include.NON_NULL)
public class Result<T> implements Serializable {
    private int code;
    private String message;
    private T data;
    public Result(ServiceException exception) {
        this.data = null;
        this.code = exception.getCode();
        this.message = exception.getMsg();
    }
    public Result(ServiceExceptionEnum exceptionEnum) {
        this.data = null;
        this.code = exceptionEnum.getCode();
        this.message = exceptionEnum.getMsg();
    }
    public Result(int code, String message) {
        this.code = code;
        this.message = message;
        this.data = null;
    }
    public Result(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
    public Result(T data) {
        this.data = data;
        this.code = 200;
        this.message = "success";
    }
}
