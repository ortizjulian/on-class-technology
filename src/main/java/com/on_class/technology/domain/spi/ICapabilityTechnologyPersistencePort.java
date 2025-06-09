package com.on_class.technology.domain.spi;

import com.on_class.technology.domain.model.CapabilityTechnology;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ICapabilityTechnologyPersistencePort {
    Mono<Void> registerCapabilityTechnologies(Long capabilityId , List<Long> technologyIds);
    Flux<CapabilityTechnology> getTechnologiesByCapabilityIds(List<Long> capabilityIds);
}
