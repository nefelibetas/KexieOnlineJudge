package com.fish.config

import com.fish.filter.RepeatableRequestFilter
import com.fish.interceptor.CustomInterceptor
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfiguration(val customInterceptor: CustomInterceptor) : WebMvcConfigurer {
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
    @Bean
    fun repeatableRequestFilterFilterRegistrationBean(): FilterRegistrationBean<RepeatableRequestFilter> {
        val bean: FilterRegistrationBean<RepeatableRequestFilter> = FilterRegistrationBean()
        bean.filter = RepeatableRequestFilter()
        bean.addUrlPatterns("/**")
        return bean
    }
}
