package com.example.springdatamongodb.controllers.exceptions;

import com.example.springdatamongodb.services.exceptions.DatabaseException;
import com.example.springdatamongodb.services.exceptions.RequestException;
import com.example.springdatamongodb.services.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
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
        standardError.setError("Parameter request exception");
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
        standardError.setError("Body request exception");
        standardError.setDescrible(e.getMessage());
        standardError.setInstant(LocalDateTime.now());
        standardError.setPath(request.getRequestURI());
        return ResponseEntity.status(status).body(standardError);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> resourceNotFoundException(ResourceNotFoundException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError standardError = new StandardError();
        standardError.setCode(status.value());
        standardError.setError("Resource not found exception");
        standardError.setDescrible(e.getMessage());
        standardError.setInstant(LocalDateTime.now());
        standardError.setPath(request.getRequestURI());
        return ResponseEntity.status(status).body(standardError);
    }

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<StandardError> databaseException(DatabaseException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError standardError = new StandardError();
        standardError.setCode(status.value());
        standardError.setError("Database exception");
        standardError.setDescrible(e.getMessage());
        standardError.setInstant(LocalDateTime.now());
        standardError.setPath(request.getRequestURI());
        return ResponseEntity.status(status).body(standardError);
    }

    @ExceptionHandler(RequestException.class)
    public ResponseEntity<StandardError> databaseException(RequestException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError standardError = new StandardError();
        standardError.setCode(status.value());
        standardError.setError("Request exception");
        standardError.setDescrible(e.getMessage());
        standardError.setInstant(LocalDateTime.now());
        standardError.setPath(request.getRequestURI());
        return ResponseEntity.status(status).body(standardError);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationError> MethodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ValidationError err = new ValidationError();
        err.setInstant(LocalDateTime.now());
        err.setCode(status.value());
        err.setError("Validation exception");
        err.setDescrible(e.getMessage());
        err.setPath(request.getRequestURI());

        for (FieldError f : e.getBindingResult().getFieldErrors()) {
            err.addError(f.getField(), f.getDefaultMessage());
        }

        return ResponseEntity.status(status).body(err);
    }
}
