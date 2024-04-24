package com.iopl.techtest.pricing.infrastructure.rest.api.find_effective_product_price;

import com.iopl.techtest.pricing.domain.ProductPrice;
import com.iopl.techtest.pricing.infrastructure.shared.mapping.BrandIdMapper;
import com.iopl.techtest.pricing.infrastructure.shared.mapping.PriceListIdMapper;
import com.iopl.techtest.pricing.infrastructure.shared.mapping.PricePriorityMapper;
import com.iopl.techtest.pricing.infrastructure.shared.mapping.ProductIdMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {BrandIdMapper.class, PriceListIdMapper.class, PricePriorityMapper.class, ProductIdMapper.class})
public interface FindEffectiveProductPriceResponseMapper {
    FindEffectiveProductPriceResponse map(ProductPrice from);
}
