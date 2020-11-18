package com.example.demo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class BearerFilter implements Filter {
    private final static String HEADER_NAME = "Authentication";
    private final static String HEADER_PREFIX = "Bearer ";
    private final BearerValidator validator;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.debug("HEJ");
        var req = (HttpServletRequest) servletRequest;
        if (req.getHeader(HEADER_NAME) != null && req.getHeader(HEADER_NAME).startsWith(HEADER_PREFIX)) {
            var bearer = req.getHeader(HEADER_NAME).substring(HEADER_PREFIX.length());
            log.debug("Got bearer");
            var authentication = validator.validate(bearer);
            if (authentication != null) SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
