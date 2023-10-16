package com.example.springreferencia.services.exceptions;

public class InvalidRuleException extends RuntimeException {

    public InvalidRuleException(String message) {
        super(message);
    }
}
