package com.fish.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fish.common.Result;
import com.fish.exception.ServiceException;
import com.fish.exception.ServiceExceptionEnum;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;

import java.io.IOException;

public class ResultUtil  {
    private static final ObjectMapper mapper = new ObjectMapper();
    /**
     * <p>用于返回无数据的请求</p>
     * @return Result类
     * @param <T> 任意数据类型
    */
    public static <T> Result<T> success() {
        return new Result<>(200, "success");
    }
    /**
     * <p>用于返回有数据的请求</p>
     * @param data:请求成功返回的数据
     * @return Result类
     * @param <T> 任意数据类型
     */
    public static <T> Result<T> success(T data) {
        return new Result<>(data);
    }
    /**
     * <span>用于全局异常处理捕获到的业务异常返回</span>
     * @param exception：出现的异常
     * @return 用异常信息代码构建返回
     * @param <T> 任意数据类型
     */
    public static <T> Result<T> failure(ServiceException exception) {
        return new Result<>(exception);
    }
    public static <T> Result<T> failure(ServiceExceptionEnum exceptionEnum) {
        return new Result<>(exceptionEnum);
    }
    /**
     * <span>用于自定义错误信息返回</span>
     * @param code: 错误代码
     * @param message: 错误信息
     * @return 返回错误响应
     * @param <T>： 基本泛型
     */
    public static <T> Result<T> failure(int code, String message) {
        return new Result<>(code, message, null);
    }
    public static void failure(HttpServletResponse response, ServiceExceptionEnum exceptionEnum) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        Result<String> result = failure(exceptionEnum);
        mapper.writeValue(response.getOutputStream(), result);
    }
}
