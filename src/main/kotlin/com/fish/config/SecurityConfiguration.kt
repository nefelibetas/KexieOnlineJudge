package com.fish.config

import com.fish.exception.ServiceExceptionEnum
import com.fish.filter.JwtLoginFilter
import com.fish.utils.ResultUtil.failure
import jakarta.annotation.Resource
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer.AuthorizationManagerRequestMatcherRegistry
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer
import org.springframework.security.config.annotation.web.configurers.ExceptionHandlingConfigurer
import org.springframework.security.core.AuthenticationException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class SecurityConfiguration {
    @Resource
    private val jwtLoginFilter: JwtLoginFilter? = null
    @Bean
    @Throws(Exception::class)
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http.authorizeHttpRequests { registry ->
//             registry.requestMatchers("/login", "/register").permitAll()
//                     .requestMatchers("/label/**").permitAll()
//                     .requestMatchers("/column/**").permitAll()
//                     .requestMatchers("/user/**").hasAnyRole("ROOT", "ADMIN", "USER")
//                     .requestMatchers("/admin/**").hasAnyRole("ROOT", "ADMIN")
//                     .requestMatchers("/root/**").hasRole("ROOT")
//                     .anyRequest().authenticated();
            // 开发环境使用，接口测试方便调用
            registry.requestMatchers("/**").permitAll()
        }
        http.addFilterBefore(jwtLoginFilter, UsernamePasswordAuthenticationFilter::class.java)
        http.csrf { obj: CsrfConfigurer<HttpSecurity> -> obj.disable() }
        http.exceptionHandling { configurer: ExceptionHandlingConfigurer<HttpSecurity?> ->
            configurer.accessDeniedHandler { _, response: HttpServletResponse, _ ->
                failure(response, ServiceExceptionEnum.INSUFFICIENT_PERMISSIONS)
            }
            configurer.authenticationEntryPoint { _, response: HttpServletResponse, _ ->
                failure(response, ServiceExceptionEnum.AUTHENTICATION_FAILURE)
            }
        }
        return http.build()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    @Throws(Exception::class)
    fun authenticationManager(configuration: AuthenticationConfiguration): AuthenticationManager {
        return configuration.getAuthenticationManager()
    }
}
