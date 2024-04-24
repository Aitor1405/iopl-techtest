package com.iopl.techtest.pricing.domain;

import java.time.Instant;
import java.util.Optional;

public interface ProductPriceRepository {
    Optional<ProductPrice> findFirstByBrandIdAndProductIdAndInstantBetweenStartAtAndEndAtOrderedByPriorityDesc(BrandId brandId, ProductId productId, Instant instant);
}
