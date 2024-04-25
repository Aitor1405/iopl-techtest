package com.iopl.techtest.pricing.infrastructure.persistence.jpa;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories
public class JpaConfig {

    @Bean
    @ConfigurationProperties("persistence.jpa")
    public JpaProperties jpaProperties() {
        return new JpaProperties();
    }

}
