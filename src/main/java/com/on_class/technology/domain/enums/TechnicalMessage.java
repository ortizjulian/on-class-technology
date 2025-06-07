package com.on_class.technology.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum TechnicalMessage {

    INVALID_REQUEST("400", "Bad Request, please verify data"),
    EMPTY_BODY("400", "Empty body, please verify data"),
    ALREADY_EXISTS("409", "Already exists, please verify data"),
    NOT_ALL_FOUND("404", "Not all found, please verify data"),
    INTERNAL_ERROR("500","Something went wrong, please try again");

    private final String code;
    private final String message;
}