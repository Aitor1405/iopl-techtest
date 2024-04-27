package com.iopl.techtest.application;

import com.iopl.techtest.pricing.application.find_effective_product_price.FindEffectiveProductPriceUseCase;
import com.iopl.techtest.pricing.application.find_effective_product_price.FindEffectiveProductPriceUseCaseImpl;
import com.iopl.techtest.pricing.domain.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FindEffectiveProductPriceUseCaseImplTests {

    private static final ZoneOffset TEST_ZONE_OFFSET = ZoneOffset.UTC;

    @Test
    public void effectiveProductPriceFound() {
        var brandId = new BrandId(1);
        var productId = new ProductId(35455);
        var startAt = LocalDateTime.of(2020, 6, 14, 0, 0, 0).toInstant(TEST_ZONE_OFFSET);
        var endAt = LocalDateTime.of(2020, 12, 31, 23, 59, 59).toInstant(TEST_ZONE_OFFSET);
        var priceListId = new PriceListId(1);
        var priority = new PricePriority(0);
        var price = new Price(new BigDecimal("35.50"), new Currency("EUR"));
        var datetime = LocalDateTime.of(2020, 6, 14, 10, 0, 0);
        var effectiveInstant = datetime.toInstant(TEST_ZONE_OFFSET);

        var expectedProductPrice = new ProductPrice(brandId, startAt, endAt, priceListId, productId, priority, price);

        var productPriceRepository = Mockito.mock(ProductPriceRepository.class);
        Mockito.when(productPriceRepository.findFirstByBrandIdAndProductIdAndInstantBetweenStartAtAndEndAtOrderedByPriorityDesc(brandId, productId, effectiveInstant))
                .thenReturn(Optional.of(expectedProductPrice));

        FindEffectiveProductPriceUseCase findEffectiveProductPriceUseCase = new FindEffectiveProductPriceUseCaseImpl(productPriceRepository);

        var effectiveProductPrice = findEffectiveProductPriceUseCase.findEffectiveProductPriceBy(brandId, productId, effectiveInstant);
        Assertions.assertEquals(expectedProductPrice, effectiveProductPrice);
    }

    @Test
    public void effectiveProductPriceNotFound() {
        var brandId = new BrandId(1);
        var productId = new ProductId(35455);

        var datetime = LocalDateTime.of(2020, 6, 14, 10, 0, 0);
        var effectiveInstant = datetime.toInstant(TEST_ZONE_OFFSET);

        var productPriceRepository = Mockito.mock(ProductPriceRepository.class);
        Mockito.when(productPriceRepository.findFirstByBrandIdAndProductIdAndInstantBetweenStartAtAndEndAtOrderedByPriorityDesc(brandId, productId, effectiveInstant))
                .thenReturn(Optional.empty());

        FindEffectiveProductPriceUseCase findEffectiveProductPriceUseCase = new FindEffectiveProductPriceUseCaseImpl(productPriceRepository);

        var effectiveProductPriceNotFoundEx = assertThrows(FindEffectiveProductPriceUseCaseImpl.FindEffectivePriceUseCaseException.NotFound.class,
                () -> findEffectiveProductPriceUseCase.findEffectiveProductPriceBy(brandId, productId, effectiveInstant));
        assertEquals("No effective product price found.", effectiveProductPriceNotFoundEx.getMessage());
        assertEquals(brandId, effectiveProductPriceNotFoundEx.getBrandId());
        assertEquals(productId, effectiveProductPriceNotFoundEx.getProductId());
        assertEquals(effectiveInstant, effectiveProductPriceNotFoundEx.getEffectiveInstant());
    }
}
