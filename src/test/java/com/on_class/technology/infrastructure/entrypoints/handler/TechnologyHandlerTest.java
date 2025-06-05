package com.on_class.technology.infrastructure.entrypoints.handler;

import com.on_class.technology.domain.api.ITechnologyServicePort;
import com.on_class.technology.domain.exceptions.BadRequestException;
import com.on_class.technology.domain.exceptions.EmptyRequestBodyException;
import com.on_class.technology.domain.model.Technology;
import com.on_class.technology.infrastructure.entrypoints.dto.TechnologyDto;
import com.on_class.technology.infrastructure.entrypoints.handler.validator.RequestValidator;
import com.on_class.technology.infrastructure.entrypoints.mapper.ITechnologyMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.server.ServerRequest;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TechnologyHandlerTest {

    @InjectMocks
    private TechnologyHandler technologyHandler;

    @Mock
    private ITechnologyServicePort technologyServicePort;

    @Mock
    private RequestValidator requestValidator;

    @Mock
    private ITechnologyMapper technologyMapper;

    @Mock
    private ServerRequest request;

    @Test
    void whenBodyIsEmpty_thenThrowBadRequestException() {

        when(request.bodyToMono(TechnologyDto.class)).thenReturn(Mono.empty());

        StepVerifier.create(technologyHandler.createTechnology(request))
                .expectError(EmptyRequestBodyException.class
                ).verify();
    }

    @Test
    void whenNameIsEmpty_thenThrowBadRequestException() {

        TechnologyDto technologyDto = new TechnologyDto();
        technologyDto.setDescription("Angular es un framework...");

        when(request.bodyToMono(TechnologyDto.class)).thenReturn(Mono.just(technologyDto));
        doThrow(new BadRequestException(List.of("Name must not be empty")))
                .when(requestValidator).validate(any());

        StepVerifier.create(technologyHandler.createTechnology(request))
                .expectError(BadRequestException.class)
                .verify();
    }

    @Test
    void whenValidBody_thenCreateTechnologySuccessfully() {
        TechnologyDto inputDto = new TechnologyDto();
        inputDto.setName("Angular");
        inputDto.setDescription("Angular es un framework de JavaScript.");

        Technology technologyDomain = new Technology(1L, "Angular", "Angular es un framework de JavaScript.");
        TechnologyDto outputDto = new TechnologyDto();
        outputDto.setName("Angular");
        outputDto.setDescription("Angular es un framework de JavaScript.");

        when(request.bodyToMono(TechnologyDto.class)).thenReturn(Mono.just(inputDto));
        doNothing().when(requestValidator).validate(inputDto);
        when(technologyMapper.toTechnology(inputDto)).thenReturn(technologyDomain);
        when(technologyServicePort.registerTechnology(technologyDomain)).thenReturn(Mono.just(technologyDomain));
        when(technologyMapper.toTechnologyDto(technologyDomain)).thenReturn(outputDto);

        StepVerifier.create(technologyHandler.createTechnology(request))
                .expectNextMatches(response -> response.statusCode().equals(HttpStatus.CREATED))
                .verifyComplete();
    }


}