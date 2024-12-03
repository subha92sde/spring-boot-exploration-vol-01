package com.spring.boot.exploration.general;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SecretKeyConfig {
    private final SecretKeyGenerator secretKeyGenerator;

    public SecretKeyConfig(SecretKeyGenerator secretKeyGenerator) {
        this.secretKeyGenerator = secretKeyGenerator;
    }

    @Bean
    @Qualifier("jwtSecretKey")
    public String jwtSecretKey() {
        return secretKeyGenerator.getSecretKey();
    }
}
