package com.on_class.technology.infrastructure.entrypoints.handler;

import com.on_class.technology.domain.api.ITechnologyServicePort;
import com.on_class.technology.domain.enums.TechnicalMessage;
import com.on_class.technology.domain.exceptions.BusinessException;
import com.on_class.technology.domain.model.CapabilityTechnology;
import com.on_class.technology.infrastructure.entrypoints.dto.CapabilityTechnologiesRequestDto;
import com.on_class.technology.infrastructure.entrypoints.dto.TechnologyDto;
import com.on_class.technology.infrastructure.entrypoints.handler.validator.RequestValidator;
import com.on_class.technology.infrastructure.entrypoints.mapper.ITechnologyMapper;
import com.on_class.technology.infrastructure.entrypoints.util.ErrorResponseBuilder;
import com.on_class.technology.infrastructure.utils.Constants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static com.on_class.technology.infrastructure.utils.Constants.TECHNOLOGY_ERROR;

@Component
@RequiredArgsConstructor
@Slf4j
public class TechnologyHandler{

    private final RequestValidator requestValidator;
    private final ITechnologyServicePort technologyServicePort;
    private final ITechnologyMapper technologyMapper;
    private final ErrorResponseBuilder responseBuilder;

    public Mono<ServerResponse> createTechnology(ServerRequest request) {
        return request.bodyToMono(TechnologyDto.class)
                .switchIfEmpty(Mono.error(new BusinessException(TechnicalMessage.EMPTY_BODY)))
                .doOnNext(requestValidator::validate)
                .map(technologyMapper::toTechnology)
                .flatMap(technologyServicePort::registerTechnology)
                .map(technologyMapper::toTechnologyDto)
                .flatMap(result -> ServerResponse.status(HttpStatus.CREATED).bodyValue(result))
                .doOnError(ex -> log.error(TECHNOLOGY_ERROR, ex))
                .onErrorResume(BusinessException.class , ex ->  responseBuilder.buildErrorResponse(
                        ex.getTechnicalMessage(),
                        ex.getDetails()
                ))
                .onErrorResume(ex ->  responseBuilder.buildErrorResponse(
                        TechnicalMessage.INTERNAL_ERROR
                ));
    }

    public Mono<ServerResponse> linkCapacities(ServerRequest request) {
        String id = request.pathVariable(Constants.CAPABILITY_ID_PATH_VARIABLE);
        Long capabilityId = Long.parseLong(id);

        return request.bodyToMono(CapabilityTechnologiesRequestDto.class)
                .switchIfEmpty(Mono.error(new BusinessException(TechnicalMessage.EMPTY_BODY)))
                .doOnNext(requestValidator::validate)
                .map(capabilityTechnologiesRequestDto ->
                     capabilityTechnologiesRequestDto.getTechnologiesIds().stream().map(techId -> new CapabilityTechnology(capabilityId, techId))
                            .toList()
                )
                .flatMap(technologyServicePort::registerCapabilityTechnologies)
                .then(ServerResponse.status(HttpStatus.CREATED).build())
                .doOnError(ex -> log.error(TECHNOLOGY_ERROR, ex))
                .onErrorResume(BusinessException.class , ex ->  responseBuilder.buildErrorResponse(
                        ex.getTechnicalMessage(),
                        ex.getDetails()
                ))
                .onErrorResume(ex ->  responseBuilder.buildErrorResponse(
                        TechnicalMessage.INTERNAL_ERROR
                ));
    }

}

