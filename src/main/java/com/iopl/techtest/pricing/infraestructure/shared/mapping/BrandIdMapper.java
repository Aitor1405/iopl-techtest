package com.iopl.techtest.pricing.infraestructure.shared.mapping;

import com.iopl.techtest.pricing.domain.BrandId;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BrandIdMapper {

    default BrandId map(Long from) {
        return new BrandId(from);
    }

    default Long map(BrandId from) {
        return from.value();
    }

}
