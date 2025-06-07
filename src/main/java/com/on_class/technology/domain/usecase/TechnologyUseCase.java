package com.on_class.technology.domain.usecase;

import com.on_class.technology.domain.api.ITechnologyServicePort;
import com.on_class.technology.domain.enums.TechnicalMessage;
import com.on_class.technology.domain.exceptions.BusinessException;
import com.on_class.technology.domain.model.CapabilityTechnology;
import com.on_class.technology.domain.model.Technology;
import com.on_class.technology.domain.spi.ICapabilityTechnologyPersistencePort;
import com.on_class.technology.domain.spi.ITechnologyPersistencePort;
import reactor.core.publisher.Mono;

import java.util.List;

public class TechnologyUseCase implements ITechnologyServicePort {

    private final ITechnologyPersistencePort technologyPersistencePort;
    private final ICapabilityTechnologyPersistencePort capabilityTechnologyPersistencePort;

    public TechnologyUseCase(ITechnologyPersistencePort technologyPersistencePort, ICapabilityTechnologyPersistencePort capabilityTechnologyPersistencePort) {
        this.technologyPersistencePort = technologyPersistencePort;
        this.capabilityTechnologyPersistencePort = capabilityTechnologyPersistencePort;
    }

    @Override
    public Mono<Technology> registerTechnology(Technology technology) {
        return technologyPersistencePort.findByName(technology.name())
                .flatMap(existing -> Mono.<Technology>error(new BusinessException(TechnicalMessage.ALREADY_EXISTS)))
                .switchIfEmpty(Mono.defer(()->technologyPersistencePort.registerTechnology(technology)));
    }

    @Override
    public Mono<Void> registerCapabilityTechnologies(List<CapabilityTechnology> capabilityTechnologyList) {
        List<Long> technologyIds = capabilityTechnologyList.stream().map((CapabilityTechnology::getTechnologyId)).toList();
        return technologyPersistencePort.existAllByIds(technologyIds)
                .flatMap(result -> Boolean.FALSE.equals(result)
                ? Mono.error(new BusinessException(TechnicalMessage.NOT_ALL_FOUND))
                : capabilityTechnologyPersistencePort.registerCapabilityTechnologies(capabilityTechnologyList)
        );
    }
}
