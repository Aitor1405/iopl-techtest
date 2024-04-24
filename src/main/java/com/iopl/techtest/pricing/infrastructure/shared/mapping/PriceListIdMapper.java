package com.iopl.techtest.pricing.infrastructure.shared.mapping;

import com.iopl.techtest.pricing.domain.PriceListId;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PriceListIdMapper {

    default PriceListId map(Long from) {
        return new PriceListId(from);
    }

    default Long map(PriceListId from) {
        return from.value();
    }

}
