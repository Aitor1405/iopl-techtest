package com.iopl.techtest.pricing.infrastructure.rest;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Order
public class GlobalRestControllerAdvice {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleDefaultException(Exception ex) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
