package com.on_class.technology.domain.exceptions;

public class EmptyRequestBodyException extends RuntimeException {
    public EmptyRequestBodyException(String message) {
        super(message);
    }
}