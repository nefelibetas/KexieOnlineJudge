package com.fish.filter

import com.fish.common.RepeatableReadRequestWrapper
import jakarta.servlet.Filter
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletRequest
import org.springframework.stereotype.Component

/**
 * 重复流过滤器,在接收到请求就将HttpServletRequest构造成可以重复使用流的类
 */
@Component
class RepeatableRequestFilter: Filter {
    override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain?) {
        val requestWrapper = RepeatableReadRequestWrapper(request as HttpServletRequest)
        chain!!.doFilter(requestWrapper, response)
    }
}