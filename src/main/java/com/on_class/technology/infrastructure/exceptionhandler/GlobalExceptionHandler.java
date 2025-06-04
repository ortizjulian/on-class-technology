package com.on_class.technology.infrastructure.exceptionhandler;


import com.on_class.technology.domain.exceptions.AlreadyExitsException;
import com.on_class.technology.domain.exceptions.BadRequestException;
import com.on_class.technology.domain.exceptions.EmptyRequestBodyException;
import com.on_class.technology.infrastructure.entrypoints.dto.ErrorDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.web.WebProperties.Resources;
import org.springframework.boot.autoconfigure.web.reactive.error.AbstractErrorWebExceptionHandler;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Mono;


@Slf4j
@Component
public class GlobalExceptionHandler extends AbstractErrorWebExceptionHandler {

    public GlobalExceptionHandler(ErrorAttributes errorAttributes, Resources resources, ApplicationContext applicationContext, ServerCodecConfigurer serverCodecConfigurer) {
        super(errorAttributes, resources, applicationContext);
        this.setMessageWriters(serverCodecConfigurer.getWriters());
        this.setMessageReaders(serverCodecConfigurer.getReaders());
    }

    @Override
    protected RouterFunction<ServerResponse> getRoutingFunction(ErrorAttributes errorAttributes) {
        return RouterFunctions.route(RequestPredicates.all(), this::renderErrorResponse);
    }

    private Mono<ServerResponse> renderErrorResponse(ServerRequest request) {
        Throwable error = getError(request);
        
        return switch (error) {
            case AlreadyExitsException e -> handleAlreadyExistsException(e);
            case BadRequestException e -> handleBadRequestException(e);
            case EmptyRequestBodyException e -> handleRequestBodyException(e);
            default -> handleGenericException(error);
        };
    }

    private Mono<ServerResponse> handleGenericException(Throwable error) {
        ErrorDto errorResponse = new ErrorDto(HttpStatus.INTERNAL_SERVER_ERROR.value(), error.getMessage());
        return ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(errorResponse);
    }

    private Mono<ServerResponse> handleAlreadyExistsException(AlreadyExitsException ex) {
        ErrorDto errorResponse = new ErrorDto(HttpStatus.CONFLICT.value(), ex.getMessage());
        return ServerResponse.status(HttpStatus.CONFLICT)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(errorResponse);
    }

    private Mono<ServerResponse> handleRequestBodyException(EmptyRequestBodyException ex) {
        ErrorDto errorResponse = new ErrorDto(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
        return ServerResponse.status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(errorResponse);
    }

    private Mono<ServerResponse> handleBadRequestException(BadRequestException ex) {
        ErrorDto errorResponse = new ErrorDto(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                ex.getErrors()
        );
        return ServerResponse.status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(errorResponse);
    }
}
