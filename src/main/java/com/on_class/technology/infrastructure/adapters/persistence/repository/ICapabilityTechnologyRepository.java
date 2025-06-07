package com.on_class.technology.infrastructure.adapters.persistence.repository;

import com.on_class.technology.infrastructure.adapters.persistence.entity.CapabilityTechnologyEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICapabilityTechnologyRepository extends ReactiveCrudRepository<CapabilityTechnologyEntity, Long> {
}
