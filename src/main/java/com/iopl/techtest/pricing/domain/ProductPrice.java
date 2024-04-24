package com.iopl.techtest.pricing.domain;

import javax.money.MonetaryAmount;
import java.time.Instant;

public record ProductPrice(BrandId brandId, Instant startAt, Instant endAt, PriceListId priceListId,
                           ProductId productId, PricePriority priority, MonetaryAmount price) {
}
