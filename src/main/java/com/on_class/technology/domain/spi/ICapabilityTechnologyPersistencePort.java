package com.on_class.technology.domain.spi;

import reactor.core.publisher.Mono;

import java.util.List;

public interface ICapabilityTechnologyPersistencePort {
    Mono<Void> registerCapabilityTechnologies(Long capabilityId , List<Long> technologyIds);
}
