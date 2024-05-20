package org.example.springsecurityoauthgoogle.controllers.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.example.springsecurityoauthgoogle.services.exceptions.ApiException;
import org.example.springsecurityoauthgoogle.services.exceptions.AuthenticationException;
import org.example.springsecurityoauthgoogle.services.exceptions.ResourceNotFoundException;
import org.example.springsecurityoauthgoogle.services.exceptions.TokenException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<StandardError> methodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError standardError = new StandardError();
        standardError.setCode(status.value());
        standardError.setError("MethodArgumentTypeMismatchException");
        standardError.setDescrible(e.getMessage());
        standardError.setInstant(LocalDateTime.now());
        standardError.setPath(request.getRequestURI());
        return ResponseEntity.status(status).body(standardError);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<StandardError> httpMessageNotReadableException(HttpMessageNotReadableException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError standardError = new StandardError();
        standardError.setCode(status.value());
        standardError.setError("HttpMessageNotReadableException");
        standardError.setDescrible(e.getMessage());
        standardError.setInstant(LocalDateTime.now());
        standardError.setPath(request.getRequestURI());
        return ResponseEntity.status(status).body(standardError);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationError> methodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ValidationError err = new ValidationError();
        err.setInstant(LocalDateTime.now());
        err.setCode(status.value());
        err.setError("MethodArgumentNotValidException");
        err.setDescrible(e.getMessage());
        err.setPath(request.getRequestURI());

        for (FieldError f : e.getBindingResult().getFieldErrors()) {
            err.addError(f.getField(), f.getDefaultMessage());
        }

        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<StandardError> apiException(ApiException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError standardError = new StandardError();
        standardError.setCode(status.value());
        standardError.setError("ApiException");
        standardError.setDescrible(e.getMessage());
        standardError.setInstant(LocalDateTime.now());
        standardError.setPath(request.getRequestURI());
        return ResponseEntity.status(status).body(standardError);
    }

    @ExceptionHandler(TokenException.class)
    public ResponseEntity<StandardError> tokenException(TokenException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError standardError = new StandardError();
        standardError.setCode(status.value());
        standardError.setError("TokenException");
        standardError.setDescrible(e.getMessage());
        standardError.setInstant(LocalDateTime.now());
        standardError.setPath(request.getRequestURI());
        return ResponseEntity.status(status).body(standardError);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> resourceNotFoundException(ResourceNotFoundException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError standardError = new StandardError();
        standardError.setCode(status.value());
        standardError.setError("ResourceNotFoundException");
        standardError.setDescrible(e.getMessage());
        standardError.setInstant(LocalDateTime.now());
        standardError.setPath(request.getRequestURI());
        return ResponseEntity.status(status).body(standardError);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<StandardError> authenticationException(AuthenticationException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.UNAUTHORIZED;
        StandardError standardError = new StandardError();
        standardError.setCode(status.value());
        standardError.setError("AuthenticationException");
        standardError.setDescrible(e.getMessage());
        standardError.setInstant(LocalDateTime.now());
        standardError.setPath(request.getRequestURI());
        return ResponseEntity.status(status).body(standardError);
    }
}
