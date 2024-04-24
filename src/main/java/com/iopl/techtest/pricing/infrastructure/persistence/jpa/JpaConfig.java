package com.iopl.techtest.pricing.infrastructure.persistence.jpa;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.time.ZoneOffset;

@Configuration
@EnableJpaRepositories
public class JpaConfig {

    // TODO: expose this on properties using profile groups
    private static final ZoneOffset DB_ZONE_OFFSET = ZoneOffset.UTC;

    public ZoneOffset getDbZoneOffset() {
        return DB_ZONE_OFFSET;
    }
}
