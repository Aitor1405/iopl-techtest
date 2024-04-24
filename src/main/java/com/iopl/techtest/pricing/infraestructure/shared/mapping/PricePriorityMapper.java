package com.iopl.techtest.pricing.infraestructure.shared.mapping;

import com.iopl.techtest.pricing.domain.PricePriority;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PricePriorityMapper {

    default PricePriority map(Long from) {
        return new PricePriority(from);
    }

    default Long map(PricePriority from) {
        return from.value();
    }

}
