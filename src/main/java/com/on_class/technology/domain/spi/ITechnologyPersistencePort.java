package com.on_class.technology.domain.spi;

import com.on_class.technology.domain.model.Technology;
import reactor.core.publisher.Mono;

public interface ITechnologyPersistencePort {
    Mono<Technology> registerTechnology(Technology technology);
    Mono<Boolean> exitsByName(String name);
}
