package com.iopl.techtest.pricing.infrastructure.persistence.jpa;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Table(name = "PRICES")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class JpaProductPrice {
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;
    @Column(name = "BRAND_ID")
    private Long brandId;
    @Column(name = "START_DATE")
    private OffsetDateTime startAt;
    @Column(name = "END_DATE")
    private OffsetDateTime endAt;
    @Column(name = "PRICE_LIST")
    private Long priceListId;
    @Column(name = "PRODUCT_ID")
    private Long productId;
    @Column(name = "PRIORITY")
    @Min(0)
    private Integer priority;
    @Column(name = "PRICE")
    private BigDecimal amount;
    @Column(name = "CURR", length = 3)
    private String currency;
}
