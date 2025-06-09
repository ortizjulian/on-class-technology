package com.on_class.technology.infrastructure.entrypoints.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class CapabilityResponseDto {
    private Long id;
    private List<TechnologyResponseDto> technologies;
}
