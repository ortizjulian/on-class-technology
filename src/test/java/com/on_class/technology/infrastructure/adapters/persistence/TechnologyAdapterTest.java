package com.on_class.technology.infrastructure.adapters.persistence;

import com.on_class.technology.infrastructure.adapters.persistence.entity.TechnologyEntity;
import com.on_class.technology.infrastructure.adapters.persistence.mapper.ITechnologyEntityMapper;
import com.on_class.technology.infrastructure.adapters.persistence.repository.ITechnologyRepository;
import org.mockito.InjectMocks;

import com.on_class.technology.domain.model.Technology;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class TechnologyAdapterTest {

    @Mock
    private ITechnologyRepository technologyRepository;

    @Mock
    private ITechnologyEntityMapper technologyMapper;

    @InjectMocks
    private TechnologyAdapter technologyAdapter;

    @Test
    void registerTechnology_shouldReturnSavedTechnology() {
        Technology technology = new Technology(1L, "Spring Boot", "Framework de Java");
        TechnologyEntity entity = new TechnologyEntity(1L, "Spring Boot", "Framework de Java");

        when(technologyMapper.toTechnologyEntity(technology)).thenReturn(entity);
        when(technologyRepository.save(entity)).thenReturn(Mono.just(entity));
        when(technologyMapper.toTechnology(entity)).thenReturn(technology);

        StepVerifier.create(technologyAdapter.registerTechnology(technology))
                .expectNext(technology)
                .verifyComplete();

        verify(technologyRepository).save(entity);
        verify(technologyMapper).toTechnologyEntity(technology);
        verify(technologyMapper).toTechnology(entity);
    }

    @Test
    void existsByName_shouldReturnTrueWhenFound() {
        String name = "Angular";
        when(technologyRepository.findByName(name)).thenReturn(Mono.just(new TechnologyEntity()));

        StepVerifier.create(technologyAdapter.existsByName(name))
                .expectNext(true)
                .verifyComplete();

        verify(technologyRepository).findByName(name);
    }

    @Test
    void existsByName_shouldReturnFalseWhenNotFound() {
        String name = "Vue";
        when(technologyRepository.findByName(name)).thenReturn(Mono.empty());

        StepVerifier.create(technologyAdapter.existsByName(name))
                .expectNext(false)
                .verifyComplete();

        verify(technologyRepository).findByName(name);
    }
}
