package com.on_class.technology.application.config;

import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;


@TestConfiguration
public class TestConfig {
    @Bean
    public WebProperties.Resources resources() {
        return new WebProperties.Resources();
    }
}