package com.iopl.techtest.pricing.infrastructure.rest.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ApiError {
    /**
     * Code should follow this format: RESOURCE.ACTION.STATUS.DESCRIPTION
     */
    private final String code;
}
