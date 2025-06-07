package com.on_class.technology.application.config;

import com.on_class.technology.domain.api.ITechnologyServicePort;
import com.on_class.technology.domain.spi.ICapabilityTechnologyPersistencePort;
import com.on_class.technology.domain.spi.ITechnologyPersistencePort;
import com.on_class.technology.domain.usecase.TechnologyUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final ITechnologyPersistencePort technologyPersistencePort;
    private final ICapabilityTechnologyPersistencePort capabilityTechnologyPersistencePort;

    @Bean
    public ITechnologyServicePort technologyServicePort() {
        return new TechnologyUseCase(technologyPersistencePort,capabilityTechnologyPersistencePort);
    }
}
