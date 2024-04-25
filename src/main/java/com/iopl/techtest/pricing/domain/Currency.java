package com.iopl.techtest.pricing.domain;

import javax.money.Monetary;
import java.util.Objects;

public record Currency(String code) {
    public Currency {
        Objects.requireNonNull(code, "Code must not be null");
        Monetary.getCurrency(code);
    }
}
