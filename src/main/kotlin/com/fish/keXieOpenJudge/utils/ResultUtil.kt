package com.fish.keXieOpenJudge.utils

import com.fasterxml.jackson.databind.ObjectMapper
import com.fish.keXieOpenJudge.common.Result
import com.fish.keXieOpenJudge.exception.ServiceException
import com.fish.keXieOpenJudge.exception.ServiceExceptionEnum
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.MediaType
import java.io.IOException
/**
 * 响应工具类
 */
object ResultUtil {
    private val mapper = ObjectMapper()

    /**
     *
     * 用于返回无数据的请求
     * @return Result类
     * @param <T> 任意数据类型
    </T> */
    @JvmStatic
    fun <T> success(): Result<T> {
        return Result(200, "success")
    }

    /**
     *
     * 用于返回有数据的请求
     * @param data:请求成功返回的数据
     * @return Result类
     * @param <T> 任意数据类型
    </T> */
    @JvmStatic
    fun <T> success(data: T): Result<T> {
        return Result(data)
    }

    /**
     * <span>用于全局异常处理捕获到的业务异常返回</span>
     * @param exception：出现的异常
     * @return 用异常信息代码构建返回
     * @param <T> 任意数据类型
    </T> */
    @JvmStatic
    fun <T> failure(exception: ServiceException): Result<T> {
        return Result(exception)
    }

    @JvmStatic
    fun <T> failure(exceptionEnum: ServiceExceptionEnum): Result<T> {
        return Result(exceptionEnum)
    }

    /**
     * <span>用于自定义错误信息返回</span>
     * @param code: 错误代码
     * @param message: 错误信息
     * @return 返回错误响应
     * @param <T>： 基本泛型
    </T> */
    @JvmStatic
    fun <T> failure(code: Int, message: String): Result<T> {
        return Result(code, message, null)
    }

    @JvmStatic
    @Throws(IOException::class)
    fun failure(response: HttpServletResponse, exceptionEnum: ServiceExceptionEnum) {
        response.contentType = MediaType.APPLICATION_JSON_VALUE
        val result = failure<String>(exceptionEnum)
        mapper.writeValue(response.outputStream, result)
    }
}
