package com.on_class.technology.infrastructure.adapters.persistence.mapper;

import com.on_class.technology.domain.model.Technology;
import com.on_class.technology.infrastructure.adapters.persistence.entity.TechnologyEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ITechnologyEntityMapper {
    TechnologyEntity toTechnologyEntity(Technology technology);
    Technology toTechnology(TechnologyEntity technologyEntity);
}
