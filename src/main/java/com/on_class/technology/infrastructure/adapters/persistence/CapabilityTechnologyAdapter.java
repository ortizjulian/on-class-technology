package com.on_class.technology.infrastructure.adapters.persistence;

import com.on_class.technology.domain.model.CapabilityTechnology;
import com.on_class.technology.domain.spi.ICapabilityTechnologyPersistencePort;
import com.on_class.technology.infrastructure.adapters.persistence.mapper.ICapabilityTechnologyMapper;
import com.on_class.technology.infrastructure.adapters.persistence.repository.ICapabilityTechnologyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CapabilityTechnologyAdapter implements ICapabilityTechnologyPersistencePort {

    private final ICapabilityTechnologyRepository capabilityTechnologyRepository;
    private final ICapabilityTechnologyMapper capabilityTechnologyMapper;

    @Override
    public Mono<Void> registerCapabilityTechnologies(List<CapabilityTechnology> capabilityTechnologyList) {
        return capabilityTechnologyRepository.saveAll(
                capabilityTechnologyMapper.toCapabilityTechnologyEntityList(capabilityTechnologyList))
                .then();
    }
}
