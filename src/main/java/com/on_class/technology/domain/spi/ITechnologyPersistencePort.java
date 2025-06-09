package com.on_class.technology.domain.spi;

import com.on_class.technology.domain.model.Technology;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ITechnologyPersistencePort {
    Mono<Technology> registerTechnology(Technology technology);
    Mono<Technology> findByName(String name);
    Mono<Boolean> existAllByIds(List<Long> ids);
    Flux<Technology> findByIds(List<Long> ids);
}
