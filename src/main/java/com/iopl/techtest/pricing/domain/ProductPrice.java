package com.iopl.techtest.pricing.domain;

import java.time.Instant;
import java.util.Objects;

public record ProductPrice(BrandId brandId, Instant startAt, Instant endAt, PriceListId priceListId,
                           ProductId productId, PricePriority priority, Price price) {
    public ProductPrice {
        Objects.requireNonNull(brandId, "Brand id must not be null");
        Objects.requireNonNull(startAt, "'Start at' must not be null");
        Objects.requireNonNull(endAt, "'End at' must not be null");
        Objects.requireNonNull(priceListId, "Price list id must not be null");
        Objects.requireNonNull(productId, "Product id must not be null");
        Objects.requireNonNull(priority, "Priority must not be null");
        Objects.requireNonNull(price, "Price must not be null");
    }
}
