package com.iopl.techtest.pricing.infraestructure.rest;

import com.fasterxml.jackson.databind.Module;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.zalando.jackson.datatype.money.MoneyModule;

@Configuration
public class JacksonConfig {

    @Bean
    public Module javaMoneyModule() {
        return new MoneyModule();
    }

}
