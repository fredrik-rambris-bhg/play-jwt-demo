package com.example.demo.security.service;

import org.springframework.security.core.Authentication;

@FunctionalInterface
public interface BearerValidator {
    Authentication validate(String bearer);
}
