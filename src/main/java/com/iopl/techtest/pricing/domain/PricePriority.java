package com.iopl.techtest.pricing.domain;

public record PricePriority(long value) implements Comparable<PricePriority> {
    public PricePriority {
        if (value < 0) {
            throw new IllegalArgumentException("Price priority cannot be less than 0");
        }
    }

    @Override
    public int compareTo(PricePriority o) {
        return Long.compare(value(), o.value());
    }
}
