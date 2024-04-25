package com.iopl.techtest.pricing.infrastructure.shared.mapping;

import com.iopl.techtest.pricing.infrastructure.shared.model.Price;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

@Mapper(componentModel = "spring", uses = CurrencyMapper.class)
public abstract class PriceMapper {

    @Autowired
    private CurrencyMapper currencyMapper;

    public Price map(com.iopl.techtest.pricing.domain.Price from) {
        return new Price(from.amount(), currencyMapper.map(from.currency()));
    }

    public com.iopl.techtest.pricing.domain.Price map(Price from) {
        return new com.iopl.techtest.pricing.domain.Price(from.amount(), currencyMapper.map(from.currency()));
    }

    public com.iopl.techtest.pricing.domain.Price map(BigDecimal amount, String currency) {
        return new com.iopl.techtest.pricing.domain.Price(amount, currencyMapper.map(currency));
    }

}
