package com.iopl.techtest.pricing.infrastructure.rest.error;

public class BadRequestApiError extends ApiError {
    public BadRequestApiError() {
        super("GENERAL.ANY.BAD_REQUEST.DEFAULT");
    }
}
