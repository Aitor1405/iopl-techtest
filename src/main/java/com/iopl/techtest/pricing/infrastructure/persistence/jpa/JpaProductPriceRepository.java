package com.iopl.techtest.pricing.infrastructure.persistence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.OffsetDateTime;
import java.util.Optional;

public interface JpaProductPriceRepository extends JpaRepository<JpaProductPrice, Long> {

    Optional<JpaProductPrice> findFirstByBrandIdAndAndProductIdAndStartAtLessThanEqualAndEndAtGreaterThanEqualOrderByPriorityDesc(
            Long brandId, Long productId, OffsetDateTime startAt, OffsetDateTime endAt
    );

}
