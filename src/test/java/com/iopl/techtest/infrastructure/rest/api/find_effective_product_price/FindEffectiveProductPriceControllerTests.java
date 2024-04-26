package com.iopl.techtest.infrastructure.rest.api.find_effective_product_price;

import com.iopl.techtest.pricing.infrastructure.persistence.jpa.JpaProductPriceRepository;
import com.iopl.techtest.pricing.infrastructure.persistence.jpa.JpaProperties;
import com.iopl.techtest.pricing.infrastructure.rest.api.find_effective_product_price.EffectivePriceNotFoundError;
import com.iopl.techtest.pricing.infrastructure.rest.api.find_effective_product_price.FindEffectiveProductPriceResponse;
import com.iopl.techtest.pricing.infrastructure.rest.error.BadRequestApiError;
import com.iopl.techtest.pricing.infrastructure.shared.model.Price;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.jdbc.Sql;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
public class FindEffectiveProductPriceControllerTests {

    @LocalServerPort
    private int port;

    @Autowired
    private JpaProperties jpaProperties;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @AfterEach
    void clearDatabase(@Autowired JpaProductPriceRepository jpaProductPriceRepository) {
        jpaProductPriceRepository.deleteAll();
    }

    @Test
    @Sql("test-data.sql")
    public void effectiveProductPriceShouldReturnFirstRecord() {
        var datetime = LocalDateTime.of(2020, 6, 14, 10, 0, 0);
        var effectiveInstant = datetime.toInstant(jpaProperties.getZoneOffset());
        var expectedResponse = new FindEffectiveProductPriceResponse(
                35455, 1, 1,
                LocalDateTime.of(2020, 6, 14, 0, 0, 0).toInstant(jpaProperties.getZoneOffset()),
                LocalDateTime.of(2020, 12, 31, 23, 59, 59).toInstant(jpaProperties.getZoneOffset()),
                new Price(new BigDecimal("35.50"), "EUR")
        );
        var url = "http://localhost:" + port + "/api/brands/" + expectedResponse.brandId() + "/products/" + expectedResponse.productId() + "/prices?effectiveInstant=" + effectiveInstant;
        var response = testRestTemplate.getForObject(url, FindEffectiveProductPriceResponse.class);
        Assertions.assertEquals(expectedResponse, response);
    }

    @Test
    @Sql("test-data.sql")
    public void effectiveProductPriceShouldReturnFirstRecord2() {
        var datetime = LocalDateTime.of(2020, 6, 14, 21, 0, 0);
        var effectiveInstant = datetime.toInstant(jpaProperties.getZoneOffset());
        var expectedResponse = new FindEffectiveProductPriceResponse(
                35455, 1, 1,
                LocalDateTime.of(2020, 6, 14, 0, 0, 0).toInstant(jpaProperties.getZoneOffset()),
                LocalDateTime.of(2020, 12, 31, 23, 59, 59).toInstant(jpaProperties.getZoneOffset()),
                new Price(new BigDecimal("35.50"), "EUR")
        );
        var url = "http://localhost:" + port + "/api/brands/" + expectedResponse.brandId() + "/products/" + expectedResponse.productId() + "/prices?effectiveInstant=" + effectiveInstant;
        var response = testRestTemplate.getForObject(url, FindEffectiveProductPriceResponse.class);
        Assertions.assertEquals(expectedResponse, response);
    }

    @Test
    @Sql("test-data.sql")
    public void effectiveProductPriceShouldReturnSecondRecord() {
        var datetime = LocalDateTime.of(2020, 6, 14, 16, 0, 0);
        var effectiveInstant = datetime.toInstant(jpaProperties.getZoneOffset());
        var expectedResponse = new FindEffectiveProductPriceResponse(
                35455, 1, 2,
                LocalDateTime.of(2020, 6, 14, 15, 0, 0).toInstant(jpaProperties.getZoneOffset()),
                LocalDateTime.of(2020, 6, 14, 18, 30, 0).toInstant(jpaProperties.getZoneOffset()),
                new Price(new BigDecimal("25.45"), "EUR")
        );
        var url = "http://localhost:" + port + "/api/brands/" + expectedResponse.brandId() + "/products/" + expectedResponse.productId() + "/prices?effectiveInstant=" + effectiveInstant;
        var response = testRestTemplate.getForObject(url, FindEffectiveProductPriceResponse.class);
        Assertions.assertEquals(expectedResponse, response);
    }

    @Test
    @Sql("test-data.sql")
    public void effectiveProductPriceShouldReturnThirdRecord() {
        var datetime = LocalDateTime.of(2020, 6, 15, 10, 0, 0);
        var effectiveInstant = datetime.toInstant(jpaProperties.getZoneOffset());
        var expectedResponse = new FindEffectiveProductPriceResponse(
                35455, 1, 3,
                LocalDateTime.of(2020, 6, 15, 0, 0, 0).toInstant(jpaProperties.getZoneOffset()),
                LocalDateTime.of(2020, 6, 15, 11, 0, 0).toInstant(jpaProperties.getZoneOffset()),
                new Price(new BigDecimal("30.50"), "EUR")
        );
        var url = "http://localhost:" + port + "/api/brands/" + expectedResponse.brandId() + "/products/" + expectedResponse.productId() + "/prices?effectiveInstant=" + effectiveInstant;
        var response = testRestTemplate.getForObject(url, FindEffectiveProductPriceResponse.class);
        Assertions.assertEquals(expectedResponse, response);
    }

    @Test
    @Sql("test-data.sql")
    public void effectiveProductPriceShouldReturnFourthRecord() {
        var datetime = LocalDateTime.of(2020, 6, 16, 21, 0, 0);
        var effectiveInstant = datetime.toInstant(jpaProperties.getZoneOffset());
        var expectedResponse = new FindEffectiveProductPriceResponse(
                35455, 1, 4,
                LocalDateTime.of(2020, 6, 15, 16, 0, 0).toInstant(jpaProperties.getZoneOffset()),
                LocalDateTime.of(2020, 12, 31, 23, 59, 59).toInstant(jpaProperties.getZoneOffset()),
                new Price(new BigDecimal("38.95"), "EUR")
        );
        var url = "http://localhost:" + port + "/api/brands/" + expectedResponse.brandId() + "/products/" + expectedResponse.productId() + "/prices?effectiveInstant=" + effectiveInstant;
        var response = testRestTemplate.getForObject(url, FindEffectiveProductPriceResponse.class);
        Assertions.assertEquals(expectedResponse, response);
    }

    @Test
    @Sql("test-data.sql")
    public void effectiveProductPriceNotFound() {
        var datetime = LocalDateTime.of(2024, 6, 16, 21, 0, 0);
        var effectiveInstant = datetime.toInstant(jpaProperties.getZoneOffset());
        var error = new EffectivePriceNotFoundError(1, 35455, effectiveInstant);
        var url = "http://localhost:" + port + "/api/brands/" + error.getBrandId() + "/products/" + error.getProductId() + "/prices?effectiveInstant=" + effectiveInstant;
        var responseEntity = testRestTemplate.getForEntity(url, EffectivePriceNotFoundError.class);
        Assertions.assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        Assertions.assertEquals(error, responseEntity.getBody());
    }

    @Test
    public void effectiveProductPriceBadRequest() {
        var url = "http://localhost:" + port + "/api/brands/1/products/2/prices?effectiveInstant=INVALID";
        var responseEntity = testRestTemplate.getForEntity(url, BadRequestApiError.class);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        Assertions.assertEquals(new BadRequestApiError(), responseEntity.getBody());
    }

}
