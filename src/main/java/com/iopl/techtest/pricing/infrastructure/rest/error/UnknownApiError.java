package com.iopl.techtest.pricing.infrastructure.rest.error;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public class UnknownApiError extends ApiError {
    public UnknownApiError() {
        super("GENERAL.ANY.INTERNAL.DEFAULT");
    }
}
