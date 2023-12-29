package com.fish.filter

import com.fasterxml.jackson.databind.ObjectMapper
import com.fish.exception.ServiceExceptionEnum
import com.fish.security.LoginAccount
import com.fish.utils.JwtUtil
import com.fish.utils.RedisUtil
import com.fish.utils.ResultUtil.failure
import io.jsonwebtoken.JwtException
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.util.StringUtils
import org.springframework.web.filter.OncePerRequestFilter
import java.io.IOException
import java.util.*

@Component
class JwtLoginFilter(
    val jwtUtil: JwtUtil,
    val redisUtil: RedisUtil
) : OncePerRequestFilter() {
    @Value("\${jwt.header}")
    val header: String? = null
    @Throws(ServletException::class, IOException::class)
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val token = request.getHeader(header)
        if (!StringUtils.hasLength(token)) {
            filterChain.doFilter(request, response)
            return
        }
        val redisKey = try {
            jwtUtil.getRedisKey(token)
        } catch (exception: JwtException) {
            failure(response, ServiceExceptionEnum.TOKEN_ERROR)
            return
        }
        val json = try {
            mapper.writeValueAsString(redisUtil.get(redisKey))
        } catch (exception :NullPointerException) {
            failure(response, ServiceExceptionEnum.UN_LOGIN)
            return
        }
        val account = mapper.readValue(json, LoginAccount::class.java)
        if (Objects.isNull(account)) {
            failure(response, ServiceExceptionEnum.UN_LOGIN)
            return
        }
        val authenticationToken =
            UsernamePasswordAuthenticationToken(account, account.account.userId, account.authorities)
        SecurityContextHolder.getContext().authentication = authenticationToken
        filterChain.doFilter(request, response)
    }
    companion object {
        private val mapper = ObjectMapper()
    }
}
