package com.fish.config;

import com.fish.exception.ServiceExceptionEnum;
import com.fish.filter.JWTLoginFilter;
import com.fish.utils.ResultUtil;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Resource
    private JWTLoginFilter jwtLoginFilter;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(registry -> {
            registry.requestMatchers("/", "/index", "/login", "/register").permitAll()
                    .requestMatchers("/user/**").hasAnyRole("ROOT", "ADMIN", "USER")
                    .requestMatchers("/admin/**").hasAnyRole("ROOT", "ADMIN")
                    .requestMatchers("/root/**").hasRole("ROOT")
                    .anyRequest().authenticated();
            // 开发环境使用，接口测试方便调用
            // registry.requestMatchers("/**").permitAll();
        });
        http.addFilterBefore(jwtLoginFilter, UsernamePasswordAuthenticationFilter.class);
        http.csrf(AbstractHttpConfigurer::disable);
        http.exceptionHandling(configurer -> {
            configurer.accessDeniedHandler((request, response, accessDeniedException) -> {
                ResultUtil.failure(response, ServiceExceptionEnum.INSUFFICIENT_PERMISSIONS);
            });
            configurer.authenticationEntryPoint((request, response, authException) -> {
                ResultUtil.failure(response, ServiceExceptionEnum.AUTHENTICATION_FAILURE);
            });
        });
        return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}
