package com.fish.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

@Component
public class CustomInterceptor implements HandlerInterceptor {
    private static final Logger log = LoggerFactory.getLogger(CustomInterceptor.class);
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String method = request.getMethod();
        String path = request.getServletPath();
        String remoteAddr = request.getRemoteAddr();
        StringBuffer buffer = new StringBuffer();
        Map<String, String[]> map = request.getParameterMap();
        map.forEach((key, val) -> {
            if (!Objects.isNull(key) && !Objects.isNull(val)) {
                buffer.append("key: ");
                buffer.append(key);
                buffer.append(",value: ");
                buffer.append(Arrays.toString(val));
                buffer.append(" ");
            }
        });
        log.info("接收到" + method + "请求: " + path + " ,来源:" + remoteAddr);
        if (StringUtils.hasLength(buffer.toString()))
            log.info("参数: " + buffer);
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
