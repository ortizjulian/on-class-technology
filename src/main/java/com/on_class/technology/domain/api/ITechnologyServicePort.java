package com.on_class.technology.domain.api;

import com.on_class.technology.domain.model.Technology;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ITechnologyServicePort {
    Mono<Technology> registerTechnology(Technology technology);
    Mono<Void> registerCapabilityTechnologies(Long capabilityId , List<Long> technologyIds);
}
