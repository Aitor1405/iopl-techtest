package com.iopl.techtest.pricing.infrastructure.persistence.jpa;

import io.hypersistence.utils.hibernate.type.money.MonetaryAmountType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;
import org.hibernate.annotations.CompositeType;

import javax.money.MonetaryAmount;
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

    @AttributeOverride(
            name = "amount",
            column = @Column(name = "PRICE")
    )
    @AttributeOverride(
            name = "currency",
            column = @Column(name = "CURR", length = 3)
    )
    @CompositeType(MonetaryAmountType.class)
    private MonetaryAmount price;
}
