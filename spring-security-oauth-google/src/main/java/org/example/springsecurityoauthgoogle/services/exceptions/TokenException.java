package org.example.springsecurityoauthgoogle.services.exceptions;

public class TokenException extends RuntimeException {

    public TokenException(String message) {
        super(message);
    }
}
