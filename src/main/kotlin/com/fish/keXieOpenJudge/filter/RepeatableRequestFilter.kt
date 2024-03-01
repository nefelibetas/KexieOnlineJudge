package com.fish.keXieOpenJudge.filter

import com.fish.keXieOpenJudge.common.RepeatableReadRequestWrapper
import jakarta.servlet.Filter
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletRequestWrapper
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.util.StringUtils

/**
 * 重复流过滤器,在接收到请求就将HttpServletRequest构造成可以重复使用流的类
 */
@Component
class RepeatableRequestFilter: Filter {
    override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain?) {
        var requestWrapper: HttpServletRequestWrapper? = null
        if (request is HttpServletRequest && StringUtils.startsWithIgnoreCase(request.contentType, MediaType.APPLICATION_JSON_VALUE))
            requestWrapper = RepeatableReadRequestWrapper(request)
        requestWrapper?.let {
            chain!!.doFilter(requestWrapper, response)
            return
        }
        chain!!.doFilter(request, response)
    }
}