package com.fish.advice

import com.fish.common.Result
import com.fish.exception.ServiceException
import com.fish.exception.ServiceExceptionEnum
import com.fish.utils.ResultUtil.failure
import io.lettuce.core.RedisConnectionException
import org.mybatis.spring.MyBatisSystemException
import org.slf4j.LoggerFactory
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.web.HttpRequestMethodNotSupportedException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.NoHandlerFoundException
import java.nio.file.AccessDeniedException
import java.util.function.Consumer

@RestControllerAdvice
class ExceptionControllerAdvice {
    /**
     * <span>用于响应业务异常</span>
     * @param exception 发生的异常
     * @return 发生异常后返回错误信息
     * @param <T> 任意数据类型
    </T> */
    @ExceptionHandler(ServiceException::class)
    fun <T> serviceHandler(exception: ServiceException): Result<T> {
        return failure(exception)
    }

    /**
     * <span>用于响应全局异常捕获到的请求方法不正确</span>
     * @param exception 请求方法异常
     * @return 返回参数不正确的响应
     * @param <T> 泛型
    </T> */
    @ExceptionHandler(HttpRequestMethodNotSupportedException::class)
    fun <T> methodsNotSupportedHandler(exception: HttpRequestMethodNotSupportedException): Result<T> {
        return failure<T>(
            ServiceExceptionEnum.METHOD_NOT_SUPPORT.code,
            exception.method + ServiceExceptionEnum.METHOD_NOT_SUPPORT.msg
        )
    }

    /**
     *
     * 用于响应捕获到的认证失败异常
     * @return 响应用户名或密码错误
     * @param <T> 泛型
    </T> */
    @ExceptionHandler(AuthenticationException::class)
    fun <T> authenticationExceptionHandler(): Result<T> {
        return failure(ServiceExceptionEnum.EMAIL_NO_PASSWORD_WRONG)
    }

    /**
     *
     * 用于响应捕获到的用户不存在异常
     * @return 响应用户不存在
     * @param <T> 泛型
    </T> */
    @ExceptionHandler(UsernameNotFoundException::class)
    fun <T> usernameNotFoundExceptionHandler(): Result<T> {
        return failure(ServiceExceptionEnum.ACCOUNT_NOT_FOUND)
    }

    /**
     *
     * 处理由用户越权访问接口造成的异常
     * @return 提示权限不足
     * @param <T> 泛型
    </T> */
    @ExceptionHandler(AccessDeniedException::class)
    fun <T> accessDeniedExceptionHandler(): Result<T> {
        return failure(ServiceExceptionEnum.INSUFFICIENT_PERMISSIONS)
    }

    /**
     *
     * 用于响应捕获到数据校验异常
     * @param exception 数据校验异常
     * @return 响应校验失败信息
     * @param <T> 泛型
    </T> */
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun <T> methodArgumentNotValidExceptionHandler(exception: MethodArgumentNotValidException): Result<T> {
        return failure(
            ServiceExceptionEnum.METHOD_ARGUMENT_NOT_VALID.code,
            ServiceExceptionEnum.METHOD_ARGUMENT_NOT_VALID.msg + getAllErrorMessage(exception)
        )
    }

    /**
     * 用于响应捕获到Redis连接的异常
     */
    @ExceptionHandler(RedisConnectionException::class)
    fun <T> redisConnectionFailureExceptionHandler(): Result<T> {
        return failure(ServiceExceptionEnum.REDIS_CONNECTION_ERROR)
    }

    /**
     *
     * 处理由MyBatis异常导致的错误，并打印到日志
     * @param exception MybatisSystemExceptionHandler
     * @return 异常信息
     * @param <T> 泛型
    </T> */
    @ExceptionHandler(MyBatisSystemException::class)
    fun <T> mybatisSystemExceptionHandler(exception: MyBatisSystemException?): Result<T> {
        log.error("MyBatis出现异常：", exception)
        return failure(ServiceExceptionEnum.MYBATIS_SYSTEM_ERROR)
    }

    /**
     * 用于响应路径参数缺失异常
     */
    @ExceptionHandler(NoHandlerFoundException ::class)
    fun <T> noHandlerFoundExceptionHandler(): Result<T> {
        return failure(ServiceExceptionEnum.PATH_VARIABLE_MISSING)
    }

    /**
     * 用于响应捕获到非法参数异常
     * @param exception 非法参数异常
     */
    @ExceptionHandler(IllegalArgumentException::class)
    fun illegalArgumentExceptionHandler(exception: IllegalArgumentException?) {
        log.error("非法参数异常: ", exception)
    }

    /**
     * 用于响应捕获到的空指针异常
     */
    @ExceptionHandler(NullPointerException::class)
    fun <T> nullPointerExceptionHandler(): Result<T> {
        return failure(ServiceExceptionEnum.NULL_POINTER)
    }

    /**
     *
     * 用于响应捕获到的未知异常
     * @param exception 未知异常
     * @return 响应出现错误
     * @param <T> 泛型
    </T> */
    @ExceptionHandler(Exception::class)
    fun <T> exceptionHandler(exception: Exception?): Result<T> {
        log.error("出现未知异常: ", exception)
        return failure(ServiceExceptionEnum.UNKNOWN_ERROR)
    }

    /**
     *
     * 处理校验异常信息
     * @param exception MethodArgumentNotValidException异常
     * @return 校验异常字符串
     */
    protected fun getAllErrorMessage(exception: MethodArgumentNotValidException): String {
        val stringBuffer = StringBuffer()
        val result = exception.bindingResult
        if (result.hasErrors()) {
            val errors = result.allErrors
            errors.run {
                forEach(Consumer { objectError -> stringBuffer.append(objectError.defaultMessage).append(" ") })
            }
        }
        return stringBuffer.toString()
    }

    companion object {
        private val log = LoggerFactory.getLogger(ExceptionControllerAdvice::class.java)
    }
}
