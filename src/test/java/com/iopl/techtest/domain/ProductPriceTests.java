package com.iopl.techtest.domain;

import com.iopl.techtest.pricing.domain.*;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ProductPriceTests {

    @Test
    public void argumentsMustNotBeNull() {
        BrandId brandId = new BrandId(1);
        Instant startAt = Instant.now();
        Instant endAt = Instant.now();
        PriceListId priceListId = new PriceListId(1);
        ProductId productId = new ProductId(33556);
        PricePriority priority = new PricePriority(2);
        Price price = new Price(BigDecimal.ZERO, new Currency("EUR"));
        NullPointerException brandIdEx = assertThrows(NullPointerException.class, () -> new ProductPrice(null, startAt, endAt, priceListId, productId, priority, price));
        assertEquals("Brand id must not be null", brandIdEx.getMessage());
        NullPointerException startAtEx = assertThrows(NullPointerException.class, () -> new ProductPrice(brandId, null, endAt, priceListId, productId, priority, price));
        assertEquals("'Start at' must not be null", startAtEx.getMessage());
        NullPointerException endAtEx = assertThrows(NullPointerException.class, () -> new ProductPrice(brandId, startAt, null, priceListId, productId, priority, price));
        assertEquals("'End at' must not be null", endAtEx.getMessage());
        NullPointerException priceListIdEx = assertThrows(NullPointerException.class, () -> new ProductPrice(brandId, startAt, endAt, null, productId, priority, price));
        assertEquals("Price list id must not be null", priceListIdEx.getMessage());
        NullPointerException productIdEx = assertThrows(NullPointerException.class, () -> new ProductPrice(brandId, startAt, endAt, priceListId, null, priority, price));
        assertEquals("Product id must not be null", productIdEx.getMessage());
        NullPointerException priorityEx = assertThrows(NullPointerException.class, () -> new ProductPrice(brandId, startAt, endAt, priceListId, productId, null, price));
        assertEquals("Priority must not be null", priorityEx.getMessage());
        NullPointerException priceEx = assertThrows(NullPointerException.class, () -> new ProductPrice(brandId, startAt, endAt, priceListId, productId, priority, null));
        assertEquals("Price must not be null", priceEx.getMessage());
    }

}
