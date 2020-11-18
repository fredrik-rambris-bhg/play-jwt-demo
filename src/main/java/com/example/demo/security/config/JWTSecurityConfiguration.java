package com.example.demo.security.config;

import com.example.demo.security.service.BearerFilter;
import com.example.demo.security.service.JWTBearerValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class JWTSecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final JWTBearerValidator validator;

    @Override
    protected void configure(HttpSecurity http) {
        log.debug("Adding BearerFilter");
        http.addFilterBefore(new BearerFilter(validator), BasicAuthenticationFilter.class);
    }
}
