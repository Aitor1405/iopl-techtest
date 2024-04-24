package com.iopl.techtest.pricing.infraestructure.rest.api.find_effective_product_price;

import javax.money.MonetaryAmount;
import java.time.Instant;

public record FindEffectiveProductPriceResponse(long productId, long brandId, long priceListId, Instant startAt, Instant endAt,
                                                MonetaryAmount price) {
}
