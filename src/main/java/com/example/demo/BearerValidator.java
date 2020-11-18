package com.example.demo;

import org.springframework.security.core.Authentication;

@FunctionalInterface
public interface BearerValidator {
    Authentication validate(String bearer);
}
