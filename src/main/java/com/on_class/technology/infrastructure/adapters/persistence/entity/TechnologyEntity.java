package com.on_class.technology.infrastructure.adapters.persistence.entity;


import com.on_class.technology.infrastructure.utils.Constants;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = Constants.TECHNOLOGY_TABLE_NAME)
@Getter
@Setter
@RequiredArgsConstructor
public class TechnologyEntity {
    @Id
    private Long id;
    private String name;
    private String description;
}
