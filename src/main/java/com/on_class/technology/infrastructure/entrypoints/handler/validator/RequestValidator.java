package com.on_class.technology.infrastructure.entrypoints.handler.validator;

import com.on_class.technology.domain.exceptions.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.List;

@Component
public class RequestValidator {

    private final Validator validator;

    @Autowired
    public RequestValidator(Validator validator) {
        this.validator = validator;
    }

    public <T> void validate(T body) {
        Errors errors = new BeanPropertyBindingResult(body, body.getClass().getName());
        validator.validate(body, errors);
        if (errors.hasErrors()) {
                List<String> errorMessages = errors.getAllErrors().stream()
                        .map(DefaultMessageSourceResolvable::getDefaultMessage)
                        .toList();

                throw new BadRequestException(errorMessages);
        }
    }
}
