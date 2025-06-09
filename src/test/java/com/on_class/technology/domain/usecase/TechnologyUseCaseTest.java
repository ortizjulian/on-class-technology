package com.on_class.technology.domain.usecase;

import com.on_class.technology.domain.exceptions.BusinessException;
import com.on_class.technology.domain.model.Technology;
import com.on_class.technology.domain.spi.ICapabilityTechnologyPersistencePort;
import com.on_class.technology.domain.spi.ITechnologyPersistencePort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;

import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class TechnologyUseCaseTest {

    @InjectMocks
    private TechnologyUseCase technologyUseCase;

    @Mock
    private ITechnologyPersistencePort technologyPersistencePort;

    @Mock
    private ICapabilityTechnologyPersistencePort capabilityTechnologyPersistencePort;

    @Test
    void registerTechnology_whenNameAlreadyExists_thenThrowsAlreadyExitsException() {
        Technology technology = new Technology(1L,"Angular", "Angular es un framework de JavaScript.");

        when(technologyPersistencePort.findByName("Angular")).thenReturn(Mono.just(technology));

        StepVerifier.create(technologyUseCase.registerTechnology(technology))
                .expectError(BusinessException.class)
                .verify();

        Mockito.verify(technologyPersistencePort).findByName("Angular");
        Mockito.verify(technologyPersistencePort, Mockito.never()).registerTechnology(Mockito.any());

    }

    @Test
    void registerTechnology_whenNameDoesNotExist_thenRegisterTechnology() {
        Technology technology = new Technology(1L, "Angular", "Angular es un framework de JavaScript.");

        when(technologyPersistencePort.findByName(technology.name())).thenReturn(Mono.empty());
        when(technologyPersistencePort.registerTechnology(technology)).thenReturn(Mono.just(technology));

        StepVerifier.create(technologyUseCase.registerTechnology(technology))
                .expectNext(technology)
                .verifyComplete();

        Mockito.verify(technologyPersistencePort).findByName(technology.name());
        Mockito.verify(technologyPersistencePort).registerTechnology(technology);
    }

    @Test
    void registerCapabilityTechnologies_whenTechnologiesExists_thenLinkCapability(){

        List<Long> technologyIds = List.of(1L,2L,3L);
        Long capabilityId = 1L;

        when(technologyPersistencePort.existAllByIds(technologyIds)).thenReturn(Mono.just(Boolean.TRUE));
        when(capabilityTechnologyPersistencePort.registerCapabilityTechnologies(capabilityId,technologyIds)).thenReturn(Mono.empty());

        StepVerifier.create(technologyUseCase.registerCapabilityTechnologies(capabilityId, technologyIds))
                .verifyComplete();

        Mockito.verify(technologyPersistencePort).existAllByIds(technologyIds);
        Mockito.verify(capabilityTechnologyPersistencePort).registerCapabilityTechnologies(capabilityId, technologyIds);

    }

    @Test
    void registerCapabilityTechnologies_shouldReturnError_whenNotAllTechnologiesExist() {
        List<Long> technologyIds = List.of(1L,2L,3L);
        Long capabilityId = 1L;

        when(technologyPersistencePort.existAllByIds(technologyIds))
                .thenReturn(Mono.just(Boolean.FALSE));

        StepVerifier.create(technologyUseCase.registerCapabilityTechnologies(capabilityId, technologyIds))
                .expectError(BusinessException.class)
                .verify();

        Mockito.verify(technologyPersistencePort).existAllByIds(technologyIds);
        Mockito.verify(capabilityTechnologyPersistencePort, Mockito.never())
                .registerCapabilityTechnologies(Mockito.anyLong(), Mockito.anyList());
    }

}