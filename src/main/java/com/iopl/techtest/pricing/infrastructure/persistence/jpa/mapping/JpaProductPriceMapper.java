package com.iopl.techtest.pricing.infrastructure.persistence.jpa.mapping;

import com.iopl.techtest.pricing.domain.ProductPrice;
import com.iopl.techtest.pricing.infrastructure.persistence.jpa.JpaProductPrice;
import com.iopl.techtest.pricing.infrastructure.persistence.jpa.JpaProperties;
import com.iopl.techtest.pricing.infrastructure.shared.mapping.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", uses = {BrandIdMapper.class, PriceListIdMapper.class, PricePriorityMapper.class, ProductIdMapper.class, PriceMapper.class})
public abstract class JpaProductPriceMapper {

    @Autowired
    protected JpaProperties jpaProperties;
    @Autowired
    protected PriceMapper priceMapper;

    @Mapping(target = "startAt", expression = "java(from.startAt().atOffset(jpaProperties.getZoneOffset()))")
    @Mapping(target = "endAt", expression = "java(from.endAt().atOffset(jpaProperties.getZoneOffset()))")
    @Mapping(target = "amount", expression = "java(from.price().amount())")
    @Mapping(target = "currency", expression = "java(from.price().currency().code())")
    public abstract JpaProductPrice map(ProductPrice from);

    @Mapping(target = "startAt", expression = "java(from.getStartAt().toInstant())")
    @Mapping(target = "endAt", expression = "java(from.getEndAt().toInstant())")
    @Mapping(target = "price", expression = "java(priceMapper.map(from.getAmount(), from.getCurrency()))")
    public abstract ProductPrice map(JpaProductPrice from);
}
