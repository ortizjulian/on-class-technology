package com.on_class.technology.infrastructure.entrypoints.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

public record ErrorDto(Integer code, String message, @JsonInclude(JsonInclude.Include.NON_NULL) List<String> errors) {

    public ErrorDto(Integer code, String message) {
        this(code, message, null);
    }
}