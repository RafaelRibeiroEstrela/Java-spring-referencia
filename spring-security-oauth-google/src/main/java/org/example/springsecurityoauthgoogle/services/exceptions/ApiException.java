package org.example.springsecurityoauthgoogle.services.exceptions;

public class ApiException extends RuntimeException {

    public ApiException(String message) {
        super(message);
    }
}
