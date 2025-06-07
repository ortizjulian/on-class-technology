package com.on_class.technology.infrastructure.adapters.persistence.entity;

import com.on_class.technology.infrastructure.utils.Constants;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table(Constants.CAPABILITY_TECHNOLOGY_TABLE_NAME)
@Getter
@Setter
public class CapabilityTechnologyEntity {

    @Id
    private Long id;

    @Column(Constants.TECHNOLOGY_COLUMN_ID)
    private Long technologyId;

    private Long capabilityId;
}
