package com.iopl.techtest.pricing.infrastructure.shared.mapping;

import com.iopl.techtest.pricing.domain.Currency;
import com.iopl.techtest.pricing.domain.PricePriority;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CurrencyMapper {

    default Currency map(String from) {
        return new Currency(from);
    }

    default String map(Currency from) {
        return from.code();
    }

}
