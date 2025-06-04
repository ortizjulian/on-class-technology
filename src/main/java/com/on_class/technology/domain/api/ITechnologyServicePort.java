package com.on_class.technology.domain.api;

import com.on_class.technology.domain.model.Technology;
import reactor.core.publisher.Mono;

public interface ITechnologyServicePort {
    Mono<Technology> registerTechnology(Technology technology);
}
