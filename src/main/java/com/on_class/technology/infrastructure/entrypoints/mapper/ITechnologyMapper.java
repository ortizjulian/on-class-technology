package com.on_class.technology.infrastructure.entrypoints.mapper;

import com.on_class.technology.domain.model.Technology;
import com.on_class.technology.infrastructure.entrypoints.dto.TechnologyDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ITechnologyMapper {
    Technology toTechnology(TechnologyDto technologyDto);
    TechnologyDto toTechnologyDto(Technology technology);
}
