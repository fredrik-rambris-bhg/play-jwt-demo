package com.example.demo;

import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.Getter;
import lombok.ToString;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@ToString
public class JWTAuthentication implements Authentication {
    private String credentials;
    private DecodedJWT details;
    private String principal;

    private List<GrantedAuthority> authorities = List.of();

    public JWTAuthentication(DecodedJWT jwt) {
        if (jwt != null) {
            details = jwt;
            principal = jwt.getSubject();
            credentials = jwt.getSignature();
            if (jwt.getAudience() != null) {
                authorities = Stream.concat(jwt.getAudience()
                        .stream(), Stream.of("ANY"))
                        .map(s -> new SimpleGrantedAuthority("ROLE_" + s))
                        .collect(Collectors.toList());
            }
        }
    }

    public boolean isAuthenticated() {
        return details != null;
    }

    @Override
    public void setAuthenticated(boolean b) throws IllegalArgumentException {

    }

    public String getName() {
        return principal;
    }
}
