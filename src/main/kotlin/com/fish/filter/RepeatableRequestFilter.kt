package com.fish.filter

import com.fish.common.RepeatableReadRequestWrapper
import jakarta.servlet.Filter
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletRequestWrapper
import org.springframework.stereotype.Component

@Component
class RepeatableRequestFilter: Filter {
    override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain?) {
        val requestWrapper = RepeatableReadRequestWrapper(request as HttpServletRequest)
        chain!!.doFilter(requestWrapper, response)
    }
}