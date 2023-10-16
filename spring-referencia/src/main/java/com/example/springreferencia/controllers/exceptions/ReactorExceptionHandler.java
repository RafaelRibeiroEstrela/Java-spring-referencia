package com.example.springreferencia.controllers.exceptions;

import com.example.springreferencia.services.exceptions.InvalidRuleException;
import com.example.springreferencia.services.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.support.WebExchangeBindException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ReactorExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionHandlerDTO> handleResourceNotFoundException(ResourceNotFoundException ex) {
        ExceptionHandlerDTO exceptionHandlerDTO = new ExceptionHandlerDTO();
        exceptionHandlerDTO.setCode(HttpStatus.NOT_FOUND.value());
        exceptionHandlerDTO.setInstant(LocalDateTime.now());
        exceptionHandlerDTO.setMessage(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionHandlerDTO);
    }

    @ExceptionHandler(WebExchangeBindException.class)
    public ResponseEntity<ExceptionHandlerDTO> webExchangeBindException(WebExchangeBindException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        ExceptionHandlerDTO exceptionHandlerDTO = new ExceptionHandlerDTO();
        exceptionHandlerDTO.setCode(HttpStatus.BAD_REQUEST.value());
        exceptionHandlerDTO.setInstant(LocalDateTime.now());
        exceptionHandlerDTO.setMessage(errors.toString());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionHandlerDTO);
    }

    @ExceptionHandler(InvalidRuleException.class)
    public ResponseEntity<ExceptionHandlerDTO> handleResourceNotFoundException(InvalidRuleException ex) {
        ExceptionHandlerDTO exceptionHandlerDTO = new ExceptionHandlerDTO();
        exceptionHandlerDTO.setCode(HttpStatus.BAD_REQUEST.value());
        exceptionHandlerDTO.setInstant(LocalDateTime.now());
        exceptionHandlerDTO.setMessage(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionHandlerDTO);
    }

    /*
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAllExceptions(Exception ex) {
        ex.printStackTrace();
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

     */

}
