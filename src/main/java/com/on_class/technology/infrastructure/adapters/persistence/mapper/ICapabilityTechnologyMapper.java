package com.on_class.technology.infrastructure.adapters.persistence.mapper;

import com.on_class.technology.domain.model.CapabilityTechnology;
import com.on_class.technology.infrastructure.adapters.persistence.entity.CapabilityTechnologyEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ICapabilityTechnologyMapper {
    List<CapabilityTechnologyEntity> toCapabilityTechnologyEntityList(List<CapabilityTechnology> capabilityTechnologyList);
}

