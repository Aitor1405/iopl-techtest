package com.iopl.techtest.pricing.infrastructure.rest.api.find_effective_product_price;

import com.iopl.techtest.pricing.application.find_effective_product_price.FindEffectiveProductPriceUseCase;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = {FindEffectiveProductPriceController.class})
@Order(Ordered.HIGHEST_PRECEDENCE)
public class FindEffectiveProductPriceControllerAdvice {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(FindEffectiveProductPriceUseCase.FindEffectivePriceUseCaseException.NotFound.class)
    public EffectivePriceNotFoundError handleNotFoundError(FindEffectiveProductPriceUseCase.FindEffectivePriceUseCaseException.NotFound ex) {
        return new EffectivePriceNotFoundError(ex.getBrandId().value(), ex.getProductId().value(), ex.getEffectiveInstant());
    }
}
