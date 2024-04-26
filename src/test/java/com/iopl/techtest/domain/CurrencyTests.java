package com.iopl.techtest.domain;

import com.iopl.techtest.pricing.domain.Currency;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CurrencyTests {

    @Test
    public void currencyCodeMustNotBeNull() {
        NullPointerException ex = assertThrows(NullPointerException.class, () -> new Currency(null));
        assertEquals("Code must not be null", ex.getMessage());
    }

    @Test
    public void currencyCannotHaveInvalidCode() {
        var code = "INVALID_CODE";
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> new Currency(code));
        assertEquals("Invalid currency code: " + code, ex.getMessage());
    }
}
