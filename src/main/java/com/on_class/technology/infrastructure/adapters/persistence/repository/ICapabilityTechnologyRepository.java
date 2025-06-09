package com.on_class.technology.infrastructure.adapters.persistence.repository;

import com.on_class.technology.infrastructure.adapters.persistence.entity.CapabilityTechnologyEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.List;

@Repository
public interface ICapabilityTechnologyRepository extends ReactiveCrudRepository<CapabilityTechnologyEntity, Long> {
    Flux<CapabilityTechnologyEntity> findByCapabilityIdIn(List<Long> capabilityIds);
}
