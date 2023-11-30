package com.fish.config

import com.fish.filter.RepeatableRequestFilter
import com.fish.interceptor.CustomInterceptor
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.servlet.DispatcherServlet
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
@EnableWebMvc
class WebConfiguration(val customInterceptor: CustomInterceptor) : WebMvcConfigurer {
    /**
     * 简单跨域支持配置
     */
    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")
            .allowedOriginPatterns("*")
            .allowCredentials(true)
            .allowedMethods(CorsConfiguration.ALL)
            .allowedHeaders(CorsConfiguration.ALL)
            .exposedHeaders("*")
            .maxAge(3600)
    }
    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(customInterceptor).addPathPatterns("/**")
    }
    /**
     * 开启mvc抛异常,配合全局异常捕获处理 路径参数 没填写的情况
     *
     * 时机：URL不在定义的全部URL内
     *
     * 弊端：可能随便写一个不存在的URL都能抛
     */
    @Bean
    fun dispatcherServlet(): DispatcherServlet {
        val dispatcherServlet = DispatcherServlet()
        dispatcherServlet.setThrowExceptionIfNoHandlerFound(true)
        return dispatcherServlet
    }
    /**
     *  日志过滤器
     *
     *  作用：将原先的HttpServletRequest内的流转化为可以多次读取的流
     */
    @Bean
    fun repeatableRequestFilterFilterRegistrationBean(): FilterRegistrationBean<RepeatableRequestFilter> {
        val bean: FilterRegistrationBean<RepeatableRequestFilter> = FilterRegistrationBean()
        bean.filter = RepeatableRequestFilter()
        bean.addUrlPatterns("/**")
        return bean
    }
}
