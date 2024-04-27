package com.iopl.techtest.pricing.application.find_effective_product_price;

import com.iopl.techtest.pricing.domain.BrandId;
import com.iopl.techtest.pricing.domain.ProductId;
import com.iopl.techtest.pricing.domain.ProductPrice;
import com.iopl.techtest.pricing.domain.ProductPriceRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class FindEffectiveProductPriceUseCaseImpl implements FindEffectiveProductPriceUseCase {

    private final ProductPriceRepository productPriceRepository;

    public ProductPrice findEffectiveProductPriceBy(BrandId brandId, ProductId productId, Instant effectiveInstant) {
        return productPriceRepository.findFirstByBrandIdAndProductIdAndInstantBetweenStartAtAndEndAtOrderedByPriorityDesc(brandId, productId, effectiveInstant)
                .orElseThrow(() -> new FindEffectivePriceUseCaseException.NotFound("No effective product price found.", brandId, productId, effectiveInstant));
    }

    @Getter
    public static class FindEffectivePriceUseCaseException extends RuntimeException {
        private final BrandId brandId;
        private final ProductId productId;
        private final Instant effectiveInstant;

        public FindEffectivePriceUseCaseException(String message, BrandId brandId, ProductId productId, Instant effectiveInstant) {
            super(message);
            this.brandId = brandId;
            this.productId = productId;
            this.effectiveInstant = effectiveInstant;
        }

        public static class NotFound extends FindEffectivePriceUseCaseException {
            public NotFound(String message, BrandId brandId, ProductId productId, Instant effectiveInstant) {
                super(message, brandId, productId, effectiveInstant);
            }
        }
    }
}
