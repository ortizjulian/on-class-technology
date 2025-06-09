package com.on_class.technology.infrastructure.entrypoints.dto;

import com.on_class.technology.infrastructure.utils.Constants;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TechnologyRequestDto {

    @NotBlank(message = Constants.EXCEPTION_TECHNOLOGY_NAME_NULL)
    @Size(min = Constants.MIN_CHARACTERS_NAME_TECHNOLOGY,
            max = Constants.MAX_CHARACTERS_NAME_TECHNOLOGY,
            message = Constants.EXCEPTION_TECHNOLOGY_NAME_SIZE)
    private String name;

    @NotBlank(message = Constants.EXCEPTION_TECHNOLOGY_DESCRIPTION_NULL)
    @Size(min = Constants.MIN_CHARACTERS_DESCRIPTION_TECHNOLOGY,
            max = Constants.MAX_CHARACTERS_DESCRIPTION_TECHNOLOGY,
            message = Constants.EXCEPTION_TECHNOLOGY_DESCRIPTION_SIZE)
    private String description;
}
