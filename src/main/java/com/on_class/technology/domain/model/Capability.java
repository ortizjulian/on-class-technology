package com.on_class.technology.domain.model;

import java.util.List;

public class Capability {
    private Long id;
    private List<Technology> technologies;

    public Capability(Long id, List<Technology> technologies) {
        this.id = id;
        this.technologies = technologies;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Technology> getTechnologies() {
        return technologies;
    }

    public void setTechnologies(List<Technology> technologies) {
        this.technologies = technologies;
    }
}
