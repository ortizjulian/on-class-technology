package com.on_class.technology.domain.model;

public class CapabilityTechnology {
    private Long technologyId;
    private Long capabilityId;

    public CapabilityTechnology(Long technologyId, Long capabilityId) {
        this.technologyId = technologyId;
        this.capabilityId = capabilityId;
    }

    public Long getTechnologyId() {
        return technologyId;
    }

    public void setTechnologyId(Long technologyId) {
        this.technologyId = technologyId;
    }

    public Long getCapabilityId() {
        return capabilityId;
    }

    public void setCapabilityId(Long capabilityId) {
        this.capabilityId = capabilityId;
    }
}
