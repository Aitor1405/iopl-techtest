package com.iopl.techtest.domain;

import com.iopl.techtest.pricing.domain.Currency;
import com.iopl.techtest.pricing.domain.Price;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PriceTests {

    @Test
    public void amountAndCurrencyMustNotBeNull() {
        NullPointerException amountEx = assertThrows(NullPointerException.class, () -> new Price(null, new Currency("EUR")));
        assertEquals("Amount must not be null", amountEx.getMessage());
        NullPointerException currencyEx = assertThrows(NullPointerException.class, () -> new Price(BigDecimal.ZERO, null));
        assertEquals("Currency must not be null", currencyEx.getMessage());
    }

}
