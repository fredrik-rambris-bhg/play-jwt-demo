package com.example.demo;

import com.example.demo.security.model.JWTAuthentication;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class Controller {
    @GetMapping
    @Secured("ROLE_ANY")
    public String root(Authentication authentication)  {
        log.info("auth={}", authentication);
        return "root";
    }

    @GetMapping("/user")
    @Secured("ROLE_USER")
    public String user(JWTAuthentication principal) {
        log.debug("{}", principal);
        return principal.getName();
    }

    @GetMapping("/admin")
    @Secured("ROLE_ADMIN")
    public String admin() {
        return "admin";
    }

}
