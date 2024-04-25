package com.iopl.techtest.pricing.infrastructure.shared.model;

import java.math.BigDecimal;

public record Price(BigDecimal amount, String currency) {
}
