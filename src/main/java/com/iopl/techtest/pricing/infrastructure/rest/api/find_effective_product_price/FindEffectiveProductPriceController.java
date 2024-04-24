package com.iopl.techtest.pricing.infrastructure.rest.api.find_effective_product_price;

import com.iopl.techtest.pricing.application.find_effective_product_price.FindEffectiveProductPriceUseCase;
import com.iopl.techtest.pricing.domain.BrandId;
import com.iopl.techtest.pricing.domain.ProductId;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/prices")
public class FindEffectiveProductPriceController {

    private final FindEffectiveProductPriceResponseMapper findEffectiveProductPriceResponseMapper;
    private final FindEffectiveProductPriceUseCase findEffectiveProductPriceUseCase;

    @GetMapping
    public FindEffectiveProductPriceResponse findEffectivePriceResponse(
            @RequestParam long brandId, @RequestParam long productId, @RequestParam Instant effectiveInstant) {
        var productPrice = findEffectiveProductPriceUseCase.findEffectiveProductPriceBy(new BrandId(brandId), new ProductId(productId), effectiveInstant);
        return findEffectiveProductPriceResponseMapper.map(productPrice);
    }

    // TODO: set @ControllerAdvice for use case errors
}
