package com.example.demo.security.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.demo.security.model.JWTAuthentication;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class JWTBearerValidator implements BearerValidator {
    private final JWTVerifier verifier;
    private static final String SECRET="mttzyXXD9cCPzPpby6XmPYknSGHcY4Af";
    private static final String ISSUER="play-purchase-service";

    public JWTBearerValidator() {

        this.verifier = JWT.require(Algorithm.HMAC256(SECRET))
                .withIssuer(ISSUER)
                .build();
        log.debug("{}", verifier);
    }

    @Override
    public Authentication validate(String b) {
        try {
            var token = verifier.verify(b);
            if (token != null) return new JWTAuthentication(token);
        } catch (JWTVerificationException e) {
            return new JWTAuthentication(e);
        }
        return null;
    }
}
