package com.example.springsecurity.components;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.springsecurity.models.User;
import com.example.springsecurity.services.exceptions.ApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class TokenManagment {

    //A CHAVE DEVE SER ARMAZENADA EM ALGUM LOCAL OCULTO E COM SEGURANCA (EX: VARIAVEL DE AMBIENTE)
    private static final String SECRET_KEY = "XPTO";
    private static final String ISSUER = "spring-security-service";
    private static final Logger LOGGER = LoggerFactory.getLogger(TokenManagment.class);

    public String generate(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
            return JWT.create()
                    .withIssuer(ISSUER)
                    .withSubject(user.getUsername())
                    .withExpiresAt(expireToken())
                    .sign(algorithm);
        } catch (JWTCreationException e) {
            throw new ApiException("Erro ao gerar token. " + e.getMessage());
        }
    }

    public String validadeToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
            return JWT.require(algorithm)
                    .withIssuer(ISSUER)
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException e) {
            LOGGER.info("JWTVerificationException: {}", e.getMessage());
            return "";
        }
    }

    private Instant expireToken() {
        //1h - 3600 segundos
        return Instant.now().plusSeconds(3600);
    }
}
