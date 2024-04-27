package com.iopl.techtest.pricing.application.find_effective_product_price;

import com.iopl.techtest.pricing.domain.BrandId;
import com.iopl.techtest.pricing.domain.ProductId;
import com.iopl.techtest.pricing.domain.ProductPrice;

import java.time.Instant;

public interface FindEffectiveProductPriceUseCase {

    ProductPrice findEffectiveProductPriceBy(BrandId brandId, ProductId productId, Instant effectiveInstant);

}
