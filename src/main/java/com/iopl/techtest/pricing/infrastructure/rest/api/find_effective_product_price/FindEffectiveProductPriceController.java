package com.iopl.techtest.pricing.infrastructure.rest.api.find_effective_product_price;

import com.iopl.techtest.pricing.application.find_effective_product_price.FindEffectiveProductPriceUseCase;
import com.iopl.techtest.pricing.domain.BrandId;
import com.iopl.techtest.pricing.domain.ProductId;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class FindEffectiveProductPriceController {

    private final FindEffectiveProductPriceResponseMapper findEffectiveProductPriceResponseMapper;
    private final FindEffectiveProductPriceUseCase findEffectiveProductPriceUseCase;

    @GetMapping("/prices/search")
    public FindEffectiveProductPriceResponse findEffectivePriceSearch(
            @RequestParam long brandId, @RequestParam long productId, @RequestParam Instant effectiveInstant) {
        var productPrice = findEffectiveProductPriceUseCase.findEffectiveProductPriceBy(new BrandId(brandId), new ProductId(productId), effectiveInstant);
        return findEffectiveProductPriceResponseMapper.map(productPrice);
    }

    @GetMapping("/brands/{brandId}/products/{productId}/prices")
    public FindEffectiveProductPriceResponse findEffectivePrice(
            @PathVariable long brandId, @PathVariable long productId, @RequestParam Instant effectiveInstant) {
        var productPrice = findEffectiveProductPriceUseCase.findEffectiveProductPriceBy(new BrandId(brandId), new ProductId(productId), effectiveInstant);
        return findEffectiveProductPriceResponseMapper.map(productPrice);
    }

}
