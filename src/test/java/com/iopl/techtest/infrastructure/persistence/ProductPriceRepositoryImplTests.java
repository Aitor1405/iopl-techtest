package com.iopl.techtest.infrastructure.persistence;

import com.iopl.techtest.pricing.domain.*;
import com.iopl.techtest.pricing.infrastructure.persistence.ProductPriceRepositoryImpl;
import com.iopl.techtest.pricing.infrastructure.persistence.jpa.JpaProductPriceRepository;
import com.iopl.techtest.pricing.infrastructure.persistence.jpa.JpaProperties;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@SpringBootTest
public class ProductPriceRepositoryImplTests {

    @Autowired
    private JpaProperties jpaProperties;

    @Autowired
    private ProductPriceRepositoryImpl productPriceRepositoryImpl;

    @AfterEach
    void clearDatabase(@Autowired JpaProductPriceRepository jpaProductPriceRepository) {
        jpaProductPriceRepository.deleteAll();
    }

    @Test
    @Sql("test-data.sql")
    public void shouldReturnFirstRecord() {
        var brandId = new BrandId(1);
        var productId = new ProductId(35455);
        var startAt = LocalDateTime.of(2020, 6, 14, 0, 0, 0).toInstant(jpaProperties.getZoneOffset());
        var endAt = LocalDateTime.of(2020, 12, 31, 23, 59, 59).toInstant(jpaProperties.getZoneOffset());
        var priceListId = new PriceListId(1);
        var priority = new PricePriority(0);
        var price = new com.iopl.techtest.pricing.domain.Price(new BigDecimal("35.50"), new Currency("EUR"));
        var expectedProductPrice = new ProductPrice(brandId, startAt, endAt, priceListId, productId, priority, price);

        var datetime = LocalDateTime.of(2020, 6, 14, 10, 0, 0);
        var effectiveInstant = datetime.toInstant(jpaProperties.getZoneOffset());

        var productPriceOpt = productPriceRepositoryImpl.findFirstByBrandIdAndProductIdAndInstantBetweenStartAtAndEndAtOrderedByPriorityDesc(brandId, productId, effectiveInstant);
        Assertions.assertTrue(productPriceOpt.isPresent());
        Assertions.assertEquals(expectedProductPrice, productPriceOpt.get());
    }

    @Test
    @Sql("test-data.sql")
    public void shouldReturnFirstRecord2() {
        var brandId = new BrandId(1);
        var productId = new ProductId(35455);
        var startAt = LocalDateTime.of(2020, 6, 14, 0, 0, 0).toInstant(jpaProperties.getZoneOffset());
        var endAt = LocalDateTime.of(2020, 12, 31, 23, 59, 59).toInstant(jpaProperties.getZoneOffset());
        var priceListId = new PriceListId(1);
        var priority = new PricePriority(0);
        var price = new com.iopl.techtest.pricing.domain.Price(new BigDecimal("35.50"), new Currency("EUR"));
        var expectedProductPrice = new ProductPrice(brandId, startAt, endAt, priceListId, productId, priority, price);

        var datetime = LocalDateTime.of(2020, 6, 14, 21, 0, 0);
        var effectiveInstant = datetime.toInstant(jpaProperties.getZoneOffset());

        var productPriceOpt = productPriceRepositoryImpl.findFirstByBrandIdAndProductIdAndInstantBetweenStartAtAndEndAtOrderedByPriorityDesc(brandId, productId, effectiveInstant);
        Assertions.assertTrue(productPriceOpt.isPresent());
        Assertions.assertEquals(expectedProductPrice, productPriceOpt.get());
    }

    @Test
    @Sql("test-data.sql")
    public void shouldReturnSecondRecord() {
        var brandId = new BrandId(1);
        var productId = new ProductId(35455);
        var startAt = LocalDateTime.of(2020, 6, 14, 15, 0, 0).toInstant(jpaProperties.getZoneOffset());
        var endAt = LocalDateTime.of(2020, 6, 14, 18, 30, 0).toInstant(jpaProperties.getZoneOffset());
        var priceListId = new PriceListId(2);
        var priority = new PricePriority(1);
        var price = new com.iopl.techtest.pricing.domain.Price(new BigDecimal("25.45"), new Currency("EUR"));
        var expectedProductPrice = new ProductPrice(brandId, startAt, endAt, priceListId, productId, priority, price);

        var datetime = LocalDateTime.of(2020, 6, 14, 16, 0, 0);
        var effectiveInstant = datetime.toInstant(jpaProperties.getZoneOffset());

        var productPriceOpt = productPriceRepositoryImpl.findFirstByBrandIdAndProductIdAndInstantBetweenStartAtAndEndAtOrderedByPriorityDesc(brandId, productId, effectiveInstant);
        Assertions.assertTrue(productPriceOpt.isPresent());
        Assertions.assertEquals(expectedProductPrice, productPriceOpt.get());
    }

    @Test
    @Sql("test-data.sql")
    public void shouldReturnThirdRecord() {
        var brandId = new BrandId(1);
        var productId = new ProductId(35455);
        var startAt = LocalDateTime.of(2020, 6, 15, 0, 0, 0).toInstant(jpaProperties.getZoneOffset());
        var endAt = LocalDateTime.of(2020, 6, 15, 11, 0, 0).toInstant(jpaProperties.getZoneOffset());
        var priceListId = new PriceListId(3);
        var priority = new PricePriority(1);
        var price = new com.iopl.techtest.pricing.domain.Price(new BigDecimal("30.50"), new Currency("EUR"));
        var expectedProductPrice = new ProductPrice(brandId, startAt, endAt, priceListId, productId, priority, price);

        var datetime = LocalDateTime.of(2020, 6, 15, 10, 0, 0);
        var effectiveInstant = datetime.toInstant(jpaProperties.getZoneOffset());

        var productPriceOpt = productPriceRepositoryImpl.findFirstByBrandIdAndProductIdAndInstantBetweenStartAtAndEndAtOrderedByPriorityDesc(brandId, productId, effectiveInstant);
        Assertions.assertTrue(productPriceOpt.isPresent());
        Assertions.assertEquals(expectedProductPrice, productPriceOpt.get());
    }

    @Test
    @Sql("test-data.sql")
    public void shouldReturnFourthRecord() {
        var brandId = new BrandId(1);
        var productId = new ProductId(35455);
        var startAt = LocalDateTime.of(2020, 6, 15, 16, 0, 0).toInstant(jpaProperties.getZoneOffset());
        var endAt = LocalDateTime.of(2020, 12, 31, 23, 59, 59).toInstant(jpaProperties.getZoneOffset());
        var priceListId = new PriceListId(4);
        var priority = new PricePriority(1);
        var price = new com.iopl.techtest.pricing.domain.Price(new BigDecimal("38.95"), new Currency("EUR"));
        var expectedProductPrice = new ProductPrice(brandId, startAt, endAt, priceListId, productId, priority, price);

        var datetime = LocalDateTime.of(2020, 6, 16, 21, 0, 0);
        var effectiveInstant = datetime.toInstant(jpaProperties.getZoneOffset());

        var productPriceOpt = productPriceRepositoryImpl.findFirstByBrandIdAndProductIdAndInstantBetweenStartAtAndEndAtOrderedByPriorityDesc(brandId, productId, effectiveInstant);
        Assertions.assertTrue(productPriceOpt.isPresent());
        Assertions.assertEquals(expectedProductPrice, productPriceOpt.get());
    }

    @Test
    @Sql("test-data.sql")
    public void shouldReturnEmpty() {
        var brandId = new BrandId(2);
        var productId = new ProductId(35455);

        var datetime = LocalDateTime.of(2020, 6, 16, 21, 0, 0);
        var effectiveInstant = datetime.toInstant(jpaProperties.getZoneOffset());

        var productPriceOpt = productPriceRepositoryImpl.findFirstByBrandIdAndProductIdAndInstantBetweenStartAtAndEndAtOrderedByPriorityDesc(brandId, productId, effectiveInstant);
        Assertions.assertTrue(productPriceOpt.isEmpty());
    }

}
