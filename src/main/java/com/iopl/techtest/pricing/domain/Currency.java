package com.iopl.techtest.pricing.domain;

import javax.money.Monetary;
import javax.money.UnknownCurrencyException;
import java.util.Objects;

public record Currency(String code) {
    public Currency {
        Objects.requireNonNull(code, "Code must not be null");
        try {
            Monetary.getCurrency(code);
        } catch (UnknownCurrencyException unknownCurrencyException) {
            throw new IllegalArgumentException("Invalid currency code: " + code, unknownCurrencyException);
        }
    }
}
