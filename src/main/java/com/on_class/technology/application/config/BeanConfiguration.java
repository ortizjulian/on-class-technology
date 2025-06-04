package com.on_class.technology.application.config;

import com.on_class.technology.domain.api.ITechnologyServicePort;
import com.on_class.technology.domain.spi.ITechnologyPersistencePort;
import com.on_class.technology.domain.usecase.TechnologyUseCase;
import com.on_class.technology.infrastructure.adapters.persistence.TechnologyAdapter;
import com.on_class.technology.infrastructure.adapters.persistence.mapper.ITechnologyEntityMapper;
import com.on_class.technology.infrastructure.adapters.persistence.repository.ITechnologyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final ITechnologyRepository technologyRepository;
    private final ITechnologyEntityMapper technologyEntityMapper;

    @Bean
    public ITechnologyServicePort technologyServicePort() {
        return new TechnologyUseCase(technologyPersistencePort());
    }

    @Bean
    public ITechnologyPersistencePort technologyPersistencePort() {
        return new TechnologyAdapter(technologyRepository,technologyEntityMapper);
    }

    @Bean
    public WebProperties.Resources webResources() {
        return new WebProperties.Resources();
    }
}
