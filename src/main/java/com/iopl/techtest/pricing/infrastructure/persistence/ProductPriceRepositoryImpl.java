package com.iopl.techtest.pricing.infrastructure.persistence;

import com.iopl.techtest.pricing.domain.BrandId;
import com.iopl.techtest.pricing.domain.ProductId;
import com.iopl.techtest.pricing.domain.ProductPrice;
import com.iopl.techtest.pricing.domain.ProductPriceRepository;
import com.iopl.techtest.pricing.infrastructure.persistence.jpa.JpaProductPriceRepository;
import com.iopl.techtest.pricing.infrastructure.persistence.jpa.JpaProperties;
import com.iopl.techtest.pricing.infrastructure.persistence.jpa.mapping.JpaProductPriceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ProductPriceRepositoryImpl implements ProductPriceRepository {

    private final JpaProperties jpaProperties;
    private final JpaProductPriceRepository jpaProductPriceRepository;
    private final JpaProductPriceMapper jpaProductPriceMapper;

    @Override
    public Optional<ProductPrice> findFirstByBrandIdAndProductIdAndInstantBetweenStartAtAndEndAtOrderedByPriorityDesc(BrandId brandId, ProductId productId, Instant instant) {
        var offsetDateTime = instant.atOffset(jpaProperties.getZoneOffset());
        return jpaProductPriceRepository.findFirstByBrandIdAndAndProductIdAndStartAtLessThanEqualAndEndAtGreaterThanEqualOrderByPriorityDesc(
                        brandId.value(), productId.value(), offsetDateTime, offsetDateTime
                )
                .map(jpaProductPriceMapper::map);
    }
}
