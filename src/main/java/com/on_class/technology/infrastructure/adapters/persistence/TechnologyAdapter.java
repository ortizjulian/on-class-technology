package com.on_class.technology.infrastructure.adapters.persistence;

import com.on_class.technology.domain.model.Technology;
import com.on_class.technology.domain.spi.ITechnologyPersistencePort;
import com.on_class.technology.infrastructure.adapters.persistence.mapper.ITechnologyEntityMapper;
import com.on_class.technology.infrastructure.adapters.persistence.repository.ITechnologyRepository;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@AllArgsConstructor
public class TechnologyAdapter implements ITechnologyPersistencePort {

    private final ITechnologyRepository technologyRepository;
    private final ITechnologyEntityMapper technologyMapper;

    @Override
    public Mono<Technology> registerTechnology(Technology technology) {
        return technologyRepository.save(technologyMapper.toTechnologyEntity(technology))
                .map(technologyMapper::toTechnology);
    }

    @Override
    public Mono<Boolean> exitsByName(String name) {
        return technologyRepository.findByName(name)
                .hasElement();
    }
}
