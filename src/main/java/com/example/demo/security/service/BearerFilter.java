package com.example.demo.security.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class BearerFilter implements Filter {
    private static final String HEADER_NAME = "Authentication";
    private static final String HEADER_PREFIX = "Bearer ";
    private final BearerValidator validator;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.debug("Checking for bearer");
        var req = (HttpServletRequest) servletRequest;
        if (req.getHeader(HEADER_NAME) != null && req.getHeader(HEADER_NAME).startsWith(HEADER_PREFIX)) {
            var bearer = req.getHeader(HEADER_NAME).substring(HEADER_PREFIX.length());
            var authentication = validator.validate(bearer);
            log.debug("{}", authentication);
            if (authentication != null) SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
