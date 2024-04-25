package com.iopl.techtest.pricing.domain;

import java.math.BigDecimal;
import java.util.Objects;

public record Price(BigDecimal amount, Currency currency) {
    public Price {
        Objects.requireNonNull(amount, "Amount must not be null");
        Objects.requireNonNull(currency, "Currency must not be null");
    }
}
