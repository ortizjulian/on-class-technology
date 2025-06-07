package com.on_class.technology.infrastructure.adapters.persistence;

import com.on_class.technology.domain.spi.ICapabilityTechnologyPersistencePort;
import com.on_class.technology.infrastructure.adapters.persistence.entity.CapabilityTechnologyEntity;
import com.on_class.technology.infrastructure.adapters.persistence.repository.ICapabilityTechnologyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CapabilityTechnologyAdapter implements ICapabilityTechnologyPersistencePort {

    private final ICapabilityTechnologyRepository capabilityTechnologyRepository;

    @Override
    public Mono<Void> registerCapabilityTechnologies(Long capabilityId, List<Long> technologyIds) {
        return Flux.fromIterable(technologyIds)
                .map(techId -> CapabilityTechnologyEntity.builder()
                        .technologyId(techId)
                        .capabilityId(capabilityId)
                        .build()
                )
                .collectList()
                .flatMapMany(capabilityTechnologyRepository::saveAll)
                .then();
    }

}
