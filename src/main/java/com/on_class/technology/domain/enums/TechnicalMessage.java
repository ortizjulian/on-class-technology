package com.on_class.technology.domain.enums;

import com.on_class.technology.domain.constants.DomainConstants;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum TechnicalMessage {

    INVALID_REQUEST(DomainConstants.STATUS_CODE_BAD_REQUEST, DomainConstants.MESSAGE_BAD_REQUEST),
    EMPTY_BODY(DomainConstants.STATUS_CODE_BAD_REQUEST, DomainConstants.MESSAGE_EMPTY_BODY),
    ALREADY_EXISTS(DomainConstants.STATUS_CODE_CONFLICT, DomainConstants.MESSAGE_ALREADY_EXISTS),
    NOT_ALL_FOUND(DomainConstants.STATUS_CODE_NOT_FOUND, DomainConstants.MESSAGE_NOT_ALL_FOUND),
    INTERNAL_ERROR(DomainConstants.STATUS_CODE_INTERNAL_ERROR, DomainConstants.MESSAGE_INTERNAL_ERROR);

    private final String code;
    private final String message;
}