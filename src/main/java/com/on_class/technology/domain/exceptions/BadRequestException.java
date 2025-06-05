package com.on_class.technology.domain.exceptions;

import com.on_class.technology.infrastructure.utils.Constants;
import lombok.Getter;

import java.util.List;

@Getter
public class BadRequestException extends RuntimeException {
    private final List<String> errors;
    public BadRequestException(List<String> errors) {
        super(Constants.EXCEPTION_DTO_VALIDATION);
        this.errors = errors;
    }
}