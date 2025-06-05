package com.on_class.technology.domain.usecase;

import com.on_class.technology.domain.exceptions.AlreadyExistsException;
import com.on_class.technology.domain.model.Technology;
import com.on_class.technology.domain.spi.ITechnologyPersistencePort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class TechnologyUseCaseTest {

    @InjectMocks
    private TechnologyUseCase technologyUseCase;

    @Mock
    private ITechnologyPersistencePort technologyPersistencePort;

    @Test
    void whenNameAlreadyExists_thenThrowsAlreadyExitsException() {
        Technology technology = new Technology(1L,"Angular", "Angular es un framework de JavaScript.");

        when(technologyPersistencePort.existsByName("Angular")).thenReturn(Mono.just(true));

        StepVerifier.create(technologyUseCase.registerTechnology(technology))
                .expectError(AlreadyExistsException.class)
                .verify();
    }

    @Test
    void whenNameDoesNotExist_thenRegisterTechnology() {
        Technology technology = new Technology(1L, "Angular", "Angular es un framework de JavaScript.");

        when(technologyPersistencePort.existsByName(technology.name())).thenReturn(Mono.just(false));
        when(technologyPersistencePort.registerTechnology(technology)).thenReturn(Mono.just(technology));

        StepVerifier.create(technologyUseCase.registerTechnology(technology))
                .expectNext(technology)
                .verifyComplete();
    }

}