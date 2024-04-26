package com.iopl.techtest.pricing.infrastructure.rest.error;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public class BadRequestApiError extends ApiError {
    public BadRequestApiError() {
        super("GENERAL.ANY.BAD_REQUEST.DEFAULT");
    }
}
