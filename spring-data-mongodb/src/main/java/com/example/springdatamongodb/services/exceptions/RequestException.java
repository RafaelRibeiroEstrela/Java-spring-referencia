package com.example.springdatamongodb.services.exceptions;

public class RequestException extends RuntimeException {

    public RequestException(String message) {
        super(message);
    }
}
