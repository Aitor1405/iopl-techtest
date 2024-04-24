package com.iopl.techtest.pricing.application.find_effective_product_price;

import com.iopl.techtest.pricing.domain.BrandId;
import com.iopl.techtest.pricing.domain.ProductId;
import com.iopl.techtest.pricing.domain.ProductPrice;
import com.iopl.techtest.pricing.domain.ProductPriceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class FindEffectiveProductPriceUseCase {

    private final ProductPriceRepository productPriceRepository;

    public ProductPrice findEffectiveProductPriceBy(BrandId brandId, ProductId productId, Instant effectiveInstant) {
        return productPriceRepository.findFirstByBrandIdAndProductIdAndInstantBetweenStartAtAndEndAtOrderedByPriorityDesc(brandId, productId, effectiveInstant)
                .orElseThrow(() -> new FindEffectivePriceUseCaseException.NotFound("No effective product price found by " + brandId + " and " + productId + " at " + effectiveInstant));
    }

    public static class FindEffectivePriceUseCaseException extends RuntimeException {

        public FindEffectivePriceUseCaseException(String message) {
            super(message);
        }

        public static class NotFound extends FindEffectivePriceUseCaseException {
            public NotFound(String message) {
                super(message);
            }
        }
    }
}
