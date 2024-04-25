package com.iopl.techtest.pricing.infrastructure.rest.error;

public class UnknownApiError extends ApiError {
    public UnknownApiError() {
        super("GENERAL.ANY.INTERNAL.DEFAULT");
    }
}
