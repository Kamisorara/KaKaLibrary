package com.kaka.framework.config;

import com.alibaba.fastjson.JSONObject;

import com.kaka.entity.result.ResultBean;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.io.IOException;

/**
 * SpringSecurity配置
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
  
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authorizeHttpRequests) ->
                        authorizeHttpRequests
                                .requestMatchers("/sys/auth/login",
                                        "/sys/auth/register"
                                ).permitAll()
                                // 其余的都需要权限校验
                                .anyRequest()
                                .authenticated()
                )
                .formLogin(
                        (LoginConfigurer ->
                                LoginConfigurer
                                        .loginProcessingUrl("/sys/auth/login")
                                        .failureHandler(this::onAuthenticationFailure)
                                        .successHandler(this::onAuthenticationSuccess)
                        )
                )
                .logout((logoutConfigurer) ->
                        logoutConfigurer
                                .logoutUrl("/sys/auth/logout")
                )
                .exceptionHandling(
                        exception ->
                                exception
                                        .authenticationEntryPoint(this::onAuthenticationFailure)
                )
                .csrf(csrf -> csrf.disable());
        return http.build();
    }


    // 登录成功响应
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication
            authentication) throws IOException, ServletException {
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(JSONObject.toJSONString(ResultBean.success("登录成功")));
    }

    // 登录失败处理
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(JSONObject.toJSONString(ResultBean.failure(401, exception.getMessage())));
    }
}
