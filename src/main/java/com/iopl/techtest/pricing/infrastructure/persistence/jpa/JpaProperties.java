package com.iopl.techtest.pricing.infrastructure.persistence.jpa;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.ZoneOffset;

@Data
public class JpaProperties {
    @NotNull
    private ZoneOffset zoneOffset;
}
