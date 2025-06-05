package com.on_class.technology.infrastructure.entrypoints.handler.validator;


import com.on_class.technology.domain.exceptions.BadRequestException;
import com.on_class.technology.infrastructure.entrypoints.dto.TechnologyDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@ExtendWith(MockitoExtension.class)
class RequestValidatorTest {

    @Mock
    private Validator validator;

    @InjectMocks
    private RequestValidator requestValidator;

    @Test
    void whenNoValidationErrors_thenDoesNothing() {
        TechnologyDto dto = new TechnologyDto();
        dto.setName("Spring");

        doAnswer(invocation -> null).when(validator).validate(any(), any());

        assertDoesNotThrow(() -> requestValidator.validate(dto));

        verify(validator, times(1)).validate(eq(dto), any());
    }

    @Test
    void whenValidationErrors_thenThrowBadRequestException() {
        TechnologyDto dto = new TechnologyDto();

        doAnswer(invocation -> {
            Errors errors = invocation.getArgument(1);
            errors.rejectValue("name", "NotNull", "El nombre no puede ser nulo");
            return null;
        }).when(validator).validate(any(), any());

        BadRequestException exception = assertThrows(BadRequestException.class, () -> requestValidator.validate(dto));

        assertEquals(1, exception.getErrors().size());
        assertTrue(exception.getErrors().getFirst().contains("El nombre no puede ser nulo"));

        verify(validator, times(1)).validate(eq(dto), any());
    }
}
