package com.on_class.technology.domain.spi;

import com.on_class.technology.domain.model.CapabilityTechnology;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ICapabilityTechnologyPersistencePort {
    Mono<Void> registerCapabilityTechnologies(List<CapabilityTechnology> capabilityTechnologyList);
}
