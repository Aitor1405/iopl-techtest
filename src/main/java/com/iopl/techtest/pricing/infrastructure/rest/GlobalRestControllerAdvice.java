package com.iopl.techtest.pricing.infrastructure.rest;

import com.iopl.techtest.pricing.infrastructure.rest.error.BadRequestApiError;
import com.iopl.techtest.pricing.infrastructure.rest.error.UnknownApiError;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
@Order
public class GlobalRestControllerAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public BadRequestApiError handle(MethodArgumentTypeMismatchException ex) {
        return new BadRequestApiError();
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RuntimeException.class)
    public UnknownApiError handle(RuntimeException ex) {
        return new UnknownApiError();
    }

}
