package org.example.springsecurityoauthgoogle.services.exceptions;

public class AuthenticationException extends RuntimeException {

    public AuthenticationException(String message) {
        super(message);
    }
}
