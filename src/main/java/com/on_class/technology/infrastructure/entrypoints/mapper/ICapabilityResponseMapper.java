package com.on_class.technology.infrastructure.entrypoints.mapper;

import com.on_class.technology.domain.model.Capability;
import com.on_class.technology.infrastructure.entrypoints.dto.CapabilityResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ICapabilityResponseMapper {
    CapabilityResponseDto toCapabilityResponseDto(Capability capability);
}
