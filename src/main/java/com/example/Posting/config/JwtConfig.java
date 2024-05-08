package com.example.Posting.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.Posting.jwt.JwtTokenFilter;
import com.example.Posting.jwt.JwtTokenProvider;

@Configuration
public class JwtConfig {

    @Bean
    public FilterRegistrationBean<JwtTokenFilter> jwtFilter() {
        final FilterRegistrationBean<JwtTokenFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new JwtTokenFilter(jwtTokenProvider()));
        registrationBean.addUrlPatterns("/api/*"); // Adjust the URL patterns as per your requirement
        return registrationBean;
    }

    @Bean
    public JwtTokenProvider jwtTokenProvider() {
        return new JwtTokenProvider();
    }
}