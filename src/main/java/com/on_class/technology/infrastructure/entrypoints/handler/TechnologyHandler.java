package com.on_class.technology.infrastructure.entrypoints.handler;

import com.on_class.technology.domain.api.ITechnologyServicePort;
import com.on_class.technology.domain.exceptions.EmptyRequestBodyException;
import com.on_class.technology.infrastructure.entrypoints.dto.TechnologyDto;
import com.on_class.technology.infrastructure.entrypoints.handler.validator.RequestValidator;
import com.on_class.technology.infrastructure.entrypoints.mapper.ITechnologyMapper;
import com.on_class.technology.infrastructure.utils.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class TechnologyHandler{

    private final RequestValidator requestValidator;
    private final ITechnologyServicePort technologyServicePort;
    private final ITechnologyMapper technologyMapper;

    public Mono<ServerResponse> createTechnology(ServerRequest request) {
        return request.bodyToMono(TechnologyDto.class)
                .switchIfEmpty(Mono.error(new EmptyRequestBodyException(Constants.EXCEPTION_EMPTY_REQUEST_BODY)))
                .doOnNext(requestValidator::validate)
                .flatMap(dto -> technologyServicePort.registerTechnology(technologyMapper.toTechnology(dto)))
                .map(technologyMapper::toTechnologyDto)
                .flatMap(result -> ServerResponse.ok().bodyValue(result));
    }
}
