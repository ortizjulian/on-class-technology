package com.on_class.technology.domain.usecase;

import com.on_class.technology.domain.exceptions.BusinessException;
import com.on_class.technology.domain.model.CapabilityTechnology;
import com.on_class.technology.domain.model.Technology;
import com.on_class.technology.domain.spi.ICapabilityTechnologyPersistencePort;
import com.on_class.technology.domain.spi.ITechnologyPersistencePort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
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

    @Test
    void getCapabilitiesTechnologies_whenCapabilityIdsProvided_thenReturnCapabilitiesWithTechnologies() {
        // Given
        List<Long> capabilityIds = List.of(1L, 2L);
        
        // Mock CapabilityTechnology objects
        CapabilityTechnology ct1 = new CapabilityTechnology(10L, 1L);
        CapabilityTechnology ct2 = new CapabilityTechnology(20L, 1L);
        CapabilityTechnology ct3 = new CapabilityTechnology(30L, 2L);
        
        // Mock Technology objects
        Technology tech1 = new Technology(10L, "Java", "Programming language");
        Technology tech2 = new Technology(20L, "Spring", "Java framework");
        Technology tech3 = new Technology(30L, "React", "JavaScript library");
        
        // Mock the getTechnologiesByCapabilityIds method
        when(capabilityTechnologyPersistencePort.getTechnologiesByCapabilityIds(capabilityIds))
                .thenReturn(Flux.just(ct1, ct2, ct3));
        
        // Mock the findByIds method for capability 1
        when(technologyPersistencePort.findByIds(List.of(10L, 20L)))
                .thenReturn(Flux.just(tech1, tech2));
        
        // Mock the findByIds method for capability 2
        when(technologyPersistencePort.findByIds(List.of(30L)))
                .thenReturn(Flux.just(tech3));
        
        // When & Then
        StepVerifier.create(technologyUseCase.getCapabilitiesTechnologies(capabilityIds))
                .expectNextMatches(capability -> 
                    capability.getId().equals(1L) && 
                    capability.getTechnologies().size() == 2 &&
                    capability.getTechnologies().get(0).id().equals(10L) &&
                    capability.getTechnologies().get(1).id().equals(20L))
                .expectNextMatches(capability -> 
                    capability.getId().equals(2L) && 
                    capability.getTechnologies().size() == 1 &&
                    capability.getTechnologies().get(0).id().equals(30L))
                .verifyComplete();
        
        // Verify interactions
        Mockito.verify(capabilityTechnologyPersistencePort).getTechnologiesByCapabilityIds(capabilityIds);
        Mockito.verify(technologyPersistencePort).findByIds(List.of(10L, 20L));
        Mockito.verify(technologyPersistencePort).findByIds(List.of(30L));
    }

    @Test
    void getCapabilitiesTechnologies_whenNoCapabilityTechnologiesFound_thenReturnEmptyFlux() {
        List<Long> capabilityIds = List.of(1L, 2L);

        when(capabilityTechnologyPersistencePort.getTechnologiesByCapabilityIds(capabilityIds))
                .thenReturn(Flux.empty());

        StepVerifier.create(technologyUseCase.getCapabilitiesTechnologies(capabilityIds))
                .verifyComplete();

        Mockito.verify(capabilityTechnologyPersistencePort).getTechnologiesByCapabilityIds(capabilityIds);
        Mockito.verify(technologyPersistencePort, Mockito.never()).findByIds(Mockito.anyList());
    }

    @Test
    void getCapabilitiesTechnologies_whenCapabilityHasNoTechnologies_thenReturnCapabilityWithEmptyTechnologies() {
        List<Long> capabilityIds = List.of(1L);

        CapabilityTechnology ct1 = new CapabilityTechnology(10L, 1L);

        when(capabilityTechnologyPersistencePort.getTechnologiesByCapabilityIds(capabilityIds))
                .thenReturn(Flux.just(ct1));

        when(technologyPersistencePort.findByIds(List.of(10L)))
                .thenReturn(Flux.empty());

        StepVerifier.create(technologyUseCase.getCapabilitiesTechnologies(capabilityIds))
                .expectNextMatches(capability -> 
                    capability.getId().equals(1L) && 
                    capability.getTechnologies().isEmpty())
                .verifyComplete();

        Mockito.verify(capabilityTechnologyPersistencePort).getTechnologiesByCapabilityIds(capabilityIds);
        Mockito.verify(technologyPersistencePort).findByIds(List.of(10L));
    }
}