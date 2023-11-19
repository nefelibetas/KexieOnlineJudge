package com.fish.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fish.exception.ServiceExceptionEnum;
import com.fish.security.LoginAccount;
import com.fish.utils.JwtUtil;
import com.fish.utils.RedisUtil;
import com.fish.utils.ResultUtil;
import io.jsonwebtoken.JwtException;
import jakarta.annotation.Resource;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

@Component
public class JWTLoginFilter extends OncePerRequestFilter {
    private static final ObjectMapper mapper = new ObjectMapper();
    @Value("${jwt.header}")
    private String HEADER;
    @Resource
    private JwtUtil jwtUtil;
    @Resource
    private RedisUtil redisUtil;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader(HEADER);
        if (!StringUtils.hasLength(token)) {
            filterChain.doFilter(request, response);
            return;
        }
        String redisKey;
        try {
            redisKey = jwtUtil.getRedisKey(token);
        } catch (JwtException exception) {
            ResultUtil.failure(response, ServiceExceptionEnum.TOKEN_ERROR);
            return;
        }
        String json = mapper.writeValueAsString(redisUtil.get(redisKey));
        LoginAccount account = mapper.readValue(json, LoginAccount.class);
        if (Objects.isNull(account)) {
            ResultUtil.failure(response, ServiceExceptionEnum.IO_ERROR);
            return;
        }
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(account, account.getAccount().getUserId(), account.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request, response);
    }
}
