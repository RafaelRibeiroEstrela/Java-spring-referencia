package com.example.springsecurity.services.exceptions;

public class TokenException extends RuntimeException {

    public TokenException(String message) {
        super(message);
    }
}
