package com.pack.spring.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<String> handleIllegalStateException(IllegalStateException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

//    @ExceptionHandler(ResourceNotFoundException.class)
//    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException ex) {
//        return new ResponseEntity<>(getMessage(), HttpStatus.NOT_FOUND);
//    }
}
