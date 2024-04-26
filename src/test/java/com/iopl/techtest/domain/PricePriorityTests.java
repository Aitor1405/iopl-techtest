package com.iopl.techtest.domain;

import com.iopl.techtest.pricing.domain.PricePriority;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PricePriorityTests {

    @Test
    public void priorityCannotBeLessThanZero() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> new PricePriority(-1));
        assertEquals("Price priority cannot be less than 0", ex.getMessage());
    }

    @Test
    public void priorityComparisons() {
        var priority1 = new PricePriority(1);
        var priority1duplicate = new PricePriority(1);
        var priority2 = new PricePriority(2);
        assertEquals(priority1, priority1duplicate);
        assertEquals(0, priority1.compareTo(priority1duplicate));
        assertEquals(-1, priority1.compareTo(priority2));
        assertEquals(1, priority2.compareTo(priority1));
    }
}
