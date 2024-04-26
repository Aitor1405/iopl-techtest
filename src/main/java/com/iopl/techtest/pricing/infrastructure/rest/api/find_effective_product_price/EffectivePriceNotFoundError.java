package com.iopl.techtest.pricing.infrastructure.rest.api.find_effective_product_price;

import com.iopl.techtest.pricing.infrastructure.rest.error.ApiError;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.Instant;

@Getter
@EqualsAndHashCode(callSuper = true)
public class EffectivePriceNotFoundError extends ApiError {

    private final long brandId;
    private final long productId;
    private final Instant effectiveInstant;

    public EffectivePriceNotFoundError(long brandId, long productId, Instant effectiveInstant) {
        super("PRICE.FIND_EFFECTIVE.NOT_FOUND.EFFECTIVE_PRICE_NOT_FOUND");
        this.brandId = brandId;
        this.productId = productId;
        this.effectiveInstant = effectiveInstant;
    }
}
