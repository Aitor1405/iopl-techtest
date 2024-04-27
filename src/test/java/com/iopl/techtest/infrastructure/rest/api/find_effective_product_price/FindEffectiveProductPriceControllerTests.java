package com.iopl.techtest.infrastructure.rest.api.find_effective_product_price;

import com.iopl.techtest.pricing.application.find_effective_product_price.FindEffectiveProductPriceUseCase;
import com.iopl.techtest.pricing.domain.*;
import com.iopl.techtest.pricing.infrastructure.rest.api.find_effective_product_price.EffectivePriceNotFoundError;
import com.iopl.techtest.pricing.infrastructure.rest.api.find_effective_product_price.FindEffectiveProductPriceResponse;
import com.iopl.techtest.pricing.infrastructure.rest.error.BadRequestApiError;
import com.iopl.techtest.pricing.infrastructure.shared.model.Price;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
public class FindEffectiveProductPriceControllerTests {

    private static final ZoneOffset TEST_ZONE_OFFSET = ZoneOffset.UTC;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @MockBean
    private FindEffectiveProductPriceUseCase findEffectiveProductPriceUseCase;


    @Test
    public void effectiveProductPricePathVariables() {
        var brandId = new BrandId(1);
        var productId = new ProductId(35455);
        var startAt = LocalDateTime.of(2020, 6, 14, 0, 0, 0).toInstant(TEST_ZONE_OFFSET);
        var endAt = LocalDateTime.of(2020, 12, 31, 23, 59, 59).toInstant(TEST_ZONE_OFFSET);
        var priceListId = new PriceListId(1);
        var priority = new PricePriority(0);
        var price = new com.iopl.techtest.pricing.domain.Price(new BigDecimal("35.50"), new Currency("EUR"));
        var datetime = LocalDateTime.of(2020, 6, 14, 10, 0, 0);
        var effectiveInstant = datetime.toInstant(TEST_ZONE_OFFSET);
        var expectedProductPrice = new ProductPrice(brandId, startAt, endAt, priceListId, productId, priority, price);
        Mockito.when(findEffectiveProductPriceUseCase.findEffectiveProductPriceBy(brandId, productId, effectiveInstant)).thenReturn(expectedProductPrice);

        var expectedResponse = new FindEffectiveProductPriceResponse(
                productId.value(), brandId.value(), priceListId.value(),
                startAt,
                endAt,
                new Price(price.amount(), price.currency().code())
        );
        var url = "http://localhost:" + port + "/api/brands/" + expectedResponse.brandId() + "/products/" + expectedResponse.productId() + "/prices?effectiveInstant=" + effectiveInstant;
        var response = testRestTemplate.getForObject(url, FindEffectiveProductPriceResponse.class);
        Assertions.assertEquals(expectedResponse, response);
    }

    @Test
    public void effectiveProductPriceSearchQuery() {
        var brandId = new BrandId(1);
        var productId = new ProductId(35455);
        var startAt = LocalDateTime.of(2020, 6, 14, 0, 0, 0).toInstant(TEST_ZONE_OFFSET);
        var endAt = LocalDateTime.of(2020, 12, 31, 23, 59, 59).toInstant(TEST_ZONE_OFFSET);
        var priceListId = new PriceListId(1);
        var priority = new PricePriority(0);
        var price = new com.iopl.techtest.pricing.domain.Price(new BigDecimal("35.50"), new Currency("EUR"));
        var datetime = LocalDateTime.of(2020, 6, 14, 10, 0, 0);
        var effectiveInstant = datetime.toInstant(TEST_ZONE_OFFSET);
        var expectedProductPrice = new ProductPrice(brandId, startAt, endAt, priceListId, productId, priority, price);
        Mockito.when(findEffectiveProductPriceUseCase.findEffectiveProductPriceBy(brandId, productId, effectiveInstant)).thenReturn(expectedProductPrice);

        var expectedResponse = new FindEffectiveProductPriceResponse(
                productId.value(), brandId.value(), priceListId.value(),
                startAt,
                endAt,
                new Price(price.amount(), price.currency().code())
        );
        var url = "http://localhost:" + port + "/api/prices/search?brandId=" + expectedResponse.brandId() + "&productId=" + expectedResponse.productId() + "&effectiveInstant=" + effectiveInstant;
        var response = testRestTemplate.getForObject(url, FindEffectiveProductPriceResponse.class);
        Assertions.assertEquals(expectedResponse, response);
    }

    @Test
    public void effectiveProductPriceNotFound() {
        var brandId = new BrandId(1);
        var productId = new ProductId(35455);
        var datetime = LocalDateTime.of(2024, 6, 16, 21, 0, 0);
        var effectiveInstant = datetime.toInstant(TEST_ZONE_OFFSET);
        var ex = new FindEffectiveProductPriceUseCase.FindEffectivePriceUseCaseException.NotFound("No effective product price found.", brandId, productId, effectiveInstant);
        var expectedError = new EffectivePriceNotFoundError(brandId.value(), productId.value(), effectiveInstant);
        Mockito.when(findEffectiveProductPriceUseCase.findEffectiveProductPriceBy(brandId, productId, effectiveInstant)).thenThrow(ex);
        var url = "http://localhost:" + port + "/api/brands/" + expectedError.getBrandId() + "/products/" + expectedError.getProductId() + "/prices?effectiveInstant=" + effectiveInstant;
        var responseEntity = testRestTemplate.getForEntity(url, EffectivePriceNotFoundError.class);
        Assertions.assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        Assertions.assertEquals(expectedError, responseEntity.getBody());
    }

    @Test
    public void effectiveProductPriceBadRequest() {
        var url = "http://localhost:" + port + "/api/brands/1/products/2/prices?effectiveInstant=INVALID";
        var responseEntity = testRestTemplate.getForEntity(url, BadRequestApiError.class);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        Assertions.assertEquals(new BadRequestApiError(), responseEntity.getBody());
    }

}
