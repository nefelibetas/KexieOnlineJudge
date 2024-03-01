package com.fish.kexieOnlineJudge.interceptor

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.util.StringUtils
import org.springframework.web.servlet.HandlerInterceptor
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

@Component
class CustomInterceptor : HandlerInterceptor {
    @Throws(Exception::class)
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val method = request.method
        val path = request.servletPath
        val remoteAddr = request.remoteAddr
        val buffer = StringBuffer()
        val map = request.parameterMap
        if (map.isNotEmpty())
            map.forEach { (key: String?, `val`: Array<String?>) ->
                if (!Objects.isNull(key) && !Objects.isNull(`val`)) {
                    buffer.append("key: ")
                    buffer.append(key)
                    buffer.append(",value: ")
                    buffer.append(`val`.contentToString())
                    buffer.append(" ")
                }
            }
        if (request.inputStream.readAllBytes().isNotEmpty())
            BufferedReader(InputStreamReader(request.inputStream)).use {
                it.forEachLine {line ->
                    buffer.append(line.trim())
                }
            }
        log.info("接收到${method}请求: ${path}, 来源: $remoteAddr")
        if (StringUtils.hasLength(buffer.toString()))
            log.info("参数: {}", buffer)
        return super.preHandle(request, response, handler)
    }
    companion object {
        private val log = LoggerFactory.getLogger(CustomInterceptor::class.java)
    }
}
