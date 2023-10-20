package com.example.springdatajpa.services.exceptions;

public class RequestException extends RuntimeException {

    public RequestException(String message) {
        super(message);
    }
}
