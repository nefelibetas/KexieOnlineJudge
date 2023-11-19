package com.fish.advice;

import com.fish.common.Result;
import com.fish.exception.ServiceException;
import com.fish.exception.ServiceExceptionEnum;
import com.fish.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.MyBatisSystemException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;
import java.util.List;

@RestControllerAdvice
@Slf4j
public class ExceptionControllerAdvice {
    /**
     * <span>用于响应业务异常</span>
     * @param exception 发生的异常
     * @return 发生异常后返回错误信息
     * @param <T> 任意数据类型
     */
    @ExceptionHandler({ServiceException.class})
    public <T> Result<T> serviceHandler(ServiceException exception) {
        return ResultUtil.failure(exception);
    }

    /**
     * <span>用于响应全局异常捕获到的请求方法不正确</span>
     * @param exception 请求方法异常
     * @return 返回参数不正确的响应
     * @param <T> 泛型
     */
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public <T> Result<T> methodsNotSupportedHandler(HttpRequestMethodNotSupportedException exception) {
        return ResultUtil.failure(ServiceExceptionEnum.METHOD_NOT_SUPPORT.getCode(),
                 exception.getMethod() + ServiceExceptionEnum.METHOD_NOT_SUPPORT.getMsg());
    }
    /**
     * <p>用于响应捕获到的认证失败异常</p>
     * @return 响应用户名或密码错误
     * @param <T> 泛型
     */
    @ExceptionHandler({AuthenticationException.class})
    public <T> Result<T> AuthenticationExceptionHandler() {
        return ResultUtil.failure(ServiceExceptionEnum.USERNAME_NO_PASSWORD_WRONG);
    }
    /**
     * <p>用于响应捕获到的用户不存在异常</p>
     * @return 响应用户不存在
     * @param <T> 泛型
     */
    @ExceptionHandler({UsernameNotFoundException.class})
    public <T> Result<T> usernameNotFoundExceptionHandler() {
        return ResultUtil.failure(ServiceExceptionEnum.ACCOUNT_NOT_FOUND);
    }
    /**
     * <p>处理由用户越权访问接口造成的异常</p>
     * @return 提示权限不足
     * @param <T> 泛型
     */
    @ExceptionHandler({AccessDeniedException.class  })
    public <T> Result<T> AccessDeniedExceptionHandler() {
        return ResultUtil.failure(ServiceExceptionEnum.INSUFFICIENT_PERMISSIONS);
    }
    /**
     * <p>用于响应捕获到数据校验异常</p>
     * @param exception 数据校验异常
     * @return 响应校验失败信息
     * @param <T> 泛型
     */
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public <T> Result<T> methodArgumentNotValidExceptionHandler (MethodArgumentNotValidException exception){
        return ResultUtil.failure(ServiceExceptionEnum.METHOD_ARGUMENT_NOT_VALID.getCode(),
                ServiceExceptionEnum.METHOD_ARGUMENT_NOT_VALID.getMsg() + getAllErrorMessage(exception));
    }
    /**
     * <p>处理由MyBatis异常导致的错误，并打印到日志</p>
     * @param exception MybatisSystemExceptionHandler
     * @return 异常信息
     * @param <T> 泛型
     */
    @ExceptionHandler({MyBatisSystemException.class})
    public <T> Result<T> MybatisSystemExceptionHandler(MyBatisSystemException exception) {
        log.error("MyBatis出现异常：", exception);
        return ResultUtil.failure(ServiceExceptionEnum.MYBATIS_SYSTEM_ERROR);
    }
    /**
     * <p>用于响应捕获到非法参数异常</p>
     * @param exception 非法参数异常
     */
    @ExceptionHandler({IllegalArgumentException.class})
    public void IllegalArgumentExceptionHandler(IllegalArgumentException exception) {
        log.error("非法参数异常: ", exception);
    }
    /**
     * <p>用于响应捕获到的未知异常</p>
     * @param exception 未知异常
     * @return 响应出现错误
     * @param <T> 泛型
     */
    @ExceptionHandler({Exception.class})
    public <T> Result<T> ExceptionHandler(Exception exception) {
        log.error("出现未知异常: ", exception);
        return ResultUtil.failure(ServiceExceptionEnum.UNKNOWN_ERROR);
    }
    /**
     * <p>处理校验异常信息</p>
     * @param exception MethodArgumentNotValidException异常
     * @return 校验异常字符串
     */
    protected String getAllErrorMessage(MethodArgumentNotValidException exception) {
        StringBuffer stringBuffer = new StringBuffer();
        BindingResult result = exception.getBindingResult();
        if (result.hasErrors()) {
            List<ObjectError> errors = result.getAllErrors();
            errors.forEach(objectError -> {
                stringBuffer.append(objectError.getDefaultMessage()).append(" ");
            });
        }
        return stringBuffer.toString();
    }
}
