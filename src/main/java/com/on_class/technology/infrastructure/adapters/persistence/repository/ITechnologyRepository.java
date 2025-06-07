package com.on_class.technology.infrastructure.adapters.persistence.repository;

import com.on_class.technology.infrastructure.adapters.persistence.entity.TechnologyEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.List;

@Repository
public interface ITechnologyRepository extends ReactiveCrudRepository<TechnologyEntity, Long> {
    Mono<TechnologyEntity> findByName(String name);
    Mono<Long> countByIdIn(List<Long> ids);
}