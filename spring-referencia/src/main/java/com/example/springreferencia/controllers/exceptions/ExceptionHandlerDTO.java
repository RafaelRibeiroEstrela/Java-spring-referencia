package com.example.springreferencia.controllers.exceptions;

import java.time.LocalDateTime;

public class ExceptionHandlerDTO {

    private int code;
    private LocalDateTime instant;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public LocalDateTime getInstant() {
        return instant;
    }

    public void setInstant(LocalDateTime instant) {
        this.instant = instant;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
