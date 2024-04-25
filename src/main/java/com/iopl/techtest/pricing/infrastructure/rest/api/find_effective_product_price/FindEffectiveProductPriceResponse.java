package com.iopl.techtest.pricing.infrastructure.rest.api.find_effective_product_price;

import com.iopl.techtest.pricing.infrastructure.shared.model.Price;

import java.time.Instant;

public record FindEffectiveProductPriceResponse(long productId, long brandId, long priceListId, Instant startAt,
                                                Instant endAt,
                                                Price price) {
}
