package com.on_class.technology.domain.usecase;

import com.on_class.technology.domain.api.ITechnologyServicePort;
import com.on_class.technology.domain.exceptions.AlreadyExitsException;
import com.on_class.technology.domain.model.Technology;
import com.on_class.technology.domain.spi.ITechnologyPersistencePort;
import com.on_class.technology.infrastructure.utils.Constants;
import reactor.core.publisher.Mono;

public class TechnologyUseCase implements ITechnologyServicePort {

    private final ITechnologyPersistencePort technologyPersistencePort;

    public TechnologyUseCase(ITechnologyPersistencePort technologyPersistencePort) {
        this.technologyPersistencePort = technologyPersistencePort;
    }

    @Override
    public Mono<Technology> registerTechnology(Technology technology) {
        return technologyPersistencePort.exitsByName(technology.name())
                .flatMap(exists -> exists
                        ? Mono.error(new AlreadyExitsException(Constants.EXCEPTION_TECHNOLOGY_ALREADY_EXITS))
                        : technologyPersistencePort.registerTechnology(technology)
                );

    }
}
