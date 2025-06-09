package com.on_class.technology.domain.constants;

public class DomainConstants {

    private DomainConstants() {
        throw new UnsupportedOperationException(UTILITY_CLASS_SHOULD_NOT_BE_INSTANTIATED);
    }

    public static final String UTILITY_CLASS_SHOULD_NOT_BE_INSTANTIATED = "Utility class should not be instantiated";

    // Status Codes
    public static final String STATUS_CODE_BAD_REQUEST = "400";
    public static final String STATUS_CODE_CONFLICT = "409";
    public static final String STATUS_CODE_NOT_FOUND = "404";
    public static final String STATUS_CODE_INTERNAL_ERROR = "500";

    // Messages
    public static final String MESSAGE_BAD_REQUEST = "Bad Request, please verify data";
    public static final String MESSAGE_EMPTY_BODY = "Empty body, please verify data";
    public static final String MESSAGE_ALREADY_EXISTS = "Already exists, please verify data";
    public static final String MESSAGE_NOT_ALL_FOUND = "Not all found, please verify data";
    public static final String MESSAGE_INTERNAL_ERROR = "Something went wrong, please try again";
}
