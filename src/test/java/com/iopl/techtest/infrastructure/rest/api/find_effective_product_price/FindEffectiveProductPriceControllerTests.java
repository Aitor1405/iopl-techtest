package com.iopl.techtest.infrastructure.rest.api.find_effective_product_price;

import com.iopl.techtest.pricing.infrastructure.persistence.jpa.JpaConfig;
import com.iopl.techtest.pricing.infrastructure.persistence.jpa.JpaProductPriceRepository;
import com.iopl.techtest.pricing.infrastructure.rest.api.find_effective_product_price.FindEffectiveProductPriceResponse;
import com.iopl.techtest.pricing.infrastructure.shared.model.Price;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.jdbc.Sql;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
public class FindEffectiveProductPriceControllerTests {

    @LocalServerPort
    private int port;

    @Autowired
    private JpaConfig jpaConfig;

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
        var effectiveInstant = datetime.toInstant(jpaConfig.getDbZoneOffset());
        var expectedResponse = new FindEffectiveProductPriceResponse(
                35455, 1, 1,
                LocalDateTime.of(2020, 6, 14, 0, 0, 0).toInstant(jpaConfig.getDbZoneOffset()),
                LocalDateTime.of(2020, 12, 31, 23, 59, 59).toInstant(jpaConfig.getDbZoneOffset()),
                new Price(new BigDecimal("35.50"), "EUR")
        );
        var url = "http://localhost:" + port + "/api/brands/" + expectedResponse.brandId() + "/products/" + expectedResponse.productId() + "?effectiveInstant=" + effectiveInstant;
        var response = testRestTemplate.getForObject(url, FindEffectiveProductPriceResponse.class);
        Assertions.assertEquals(expectedResponse, response);
    }

    @Test
    @Sql("test-data.sql")
    public void effectiveProductPriceShouldReturnFirstRecord2() {
        var datetime = LocalDateTime.of(2020, 6, 14, 21, 0, 0);
        var effectiveInstant = datetime.toInstant(jpaConfig.getDbZoneOffset());
        var expectedResponse = new FindEffectiveProductPriceResponse(
                35455, 1, 1,
                LocalDateTime.of(2020, 6, 14, 0, 0, 0).toInstant(jpaConfig.getDbZoneOffset()),
                LocalDateTime.of(2020, 12, 31, 23, 59, 59).toInstant(jpaConfig.getDbZoneOffset()),
                new Price(new BigDecimal("35.50"), "EUR")
        );
        var url = "http://localhost:" + port + "/api/brands/" + expectedResponse.brandId() + "/products/" + expectedResponse.productId() + "?effectiveInstant=" + effectiveInstant;
        var response = testRestTemplate.getForObject(url, FindEffectiveProductPriceResponse.class);
        Assertions.assertEquals(expectedResponse, response);
    }

    @Test
    @Sql("test-data.sql")
    public void effectiveProductPriceShouldReturnSecondRecord() {
        var datetime = LocalDateTime.of(2020, 6, 14, 16, 0, 0);
        var effectiveInstant = datetime.toInstant(jpaConfig.getDbZoneOffset());
        var expectedResponse = new FindEffectiveProductPriceResponse(
                35455, 1, 2,
                LocalDateTime.of(2020, 6, 14, 15, 0, 0).toInstant(jpaConfig.getDbZoneOffset()),
                LocalDateTime.of(2020, 6, 14, 18, 30, 0).toInstant(jpaConfig.getDbZoneOffset()),
                new Price(new BigDecimal("25.45"), "EUR")
        );
        var url = "http://localhost:" + port + "/api/brands/" + expectedResponse.brandId() + "/products/" + expectedResponse.productId() + "?effectiveInstant=" + effectiveInstant;
        var response = testRestTemplate.getForObject(url, FindEffectiveProductPriceResponse.class);
        Assertions.assertEquals(expectedResponse, response);
    }

    @Test
    @Sql("test-data.sql")
    public void effectiveProductPriceShouldReturnThirdRecord() {
        var datetime = LocalDateTime.of(2020, 6, 15, 10, 0, 0);
        var effectiveInstant = datetime.toInstant(jpaConfig.getDbZoneOffset());
        var expectedResponse = new FindEffectiveProductPriceResponse(
                35455, 1, 3,
                LocalDateTime.of(2020, 6, 15, 0, 0, 0).toInstant(jpaConfig.getDbZoneOffset()),
                LocalDateTime.of(2020, 6, 15, 11, 0, 0).toInstant(jpaConfig.getDbZoneOffset()),
                new Price(new BigDecimal("30.50"), "EUR")
        );
        var url = "http://localhost:" + port + "/api/brands/" + expectedResponse.brandId() + "/products/" + expectedResponse.productId() + "?effectiveInstant=" + effectiveInstant;
        var response = testRestTemplate.getForObject(url, FindEffectiveProductPriceResponse.class);
        Assertions.assertEquals(expectedResponse, response);
    }

    @Test
    @Sql("test-data.sql")
    public void effectiveProductPriceShouldReturnFourthRecord() {
        var datetime = LocalDateTime.of(2020, 6, 16, 21, 0, 0);
        var effectiveInstant = datetime.toInstant(jpaConfig.getDbZoneOffset());
        var expectedResponse = new FindEffectiveProductPriceResponse(
                35455, 1, 4,
                LocalDateTime.of(2020, 6, 15, 16, 0, 0).toInstant(jpaConfig.getDbZoneOffset()),
                LocalDateTime.of(2020, 12, 31, 23, 59, 59).toInstant(jpaConfig.getDbZoneOffset()),
                new Price(new BigDecimal("38.95"), "EUR")
        );
        var url = "http://localhost:" + port + "/api/brands/" + expectedResponse.brandId() + "/products/" + expectedResponse.productId() + "?effectiveInstant=" + effectiveInstant;
        var response = testRestTemplate.getForObject(url, FindEffectiveProductPriceResponse.class);
        Assertions.assertEquals(expectedResponse, response);
    }

}
