package com.iopl.techtest.pricing.infrastructure.shared.mapping;

import com.iopl.techtest.pricing.domain.ProductId;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductIdMapper {

    default ProductId map(Long from) {
        return new ProductId(from);
    }

    default Long map(ProductId from) {
        return from.value();
    }

}
