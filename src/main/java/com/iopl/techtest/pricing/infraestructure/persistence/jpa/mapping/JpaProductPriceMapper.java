package com.iopl.techtest.pricing.infraestructure.persistence.jpa.mapping;

import com.iopl.techtest.pricing.domain.ProductPrice;
import com.iopl.techtest.pricing.infraestructure.persistence.jpa.JpaConfig;
import com.iopl.techtest.pricing.infraestructure.persistence.jpa.JpaProductPrice;
import com.iopl.techtest.pricing.infraestructure.shared.mapping.BrandIdMapper;
import com.iopl.techtest.pricing.infraestructure.shared.mapping.PriceListIdMapper;
import com.iopl.techtest.pricing.infraestructure.shared.mapping.PricePriorityMapper;
import com.iopl.techtest.pricing.infraestructure.shared.mapping.ProductIdMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", uses = {BrandIdMapper.class, PriceListIdMapper.class, PricePriorityMapper.class, ProductIdMapper.class})
public abstract class JpaProductPriceMapper {

    @Autowired
    protected JpaConfig jpaConfig;

    @Mapping(target = "startAt", expression = "java(from.startAt().atOffset(jpaConfig.getDbZoneOffset()))")
    @Mapping(target = "endAt", expression = "java(from.endAt().atOffset(jpaConfig.getDbZoneOffset()))")
    public abstract JpaProductPrice map(ProductPrice from);

    @Mapping(target = "startAt", expression = "java(from.getStartAt().toInstant())")
    @Mapping(target = "endAt", expression = "java(from.getEndAt().toInstant())")
    public abstract ProductPrice map(JpaProductPrice from);
}
