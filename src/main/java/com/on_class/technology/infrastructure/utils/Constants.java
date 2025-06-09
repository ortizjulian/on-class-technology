package com.on_class.technology.infrastructure.utils;

import static com.on_class.technology.domain.constants.DomainConstants.UTILITY_CLASS_SHOULD_NOT_BE_INSTANTIATED;

public class Constants {

    private Constants() {
        throw new UnsupportedOperationException(UTILITY_CLASS_SHOULD_NOT_BE_INSTANTIATED);
    }
    //Routes
    public static final String ROUTE_TECHNOLOGY= "/technology";
    public static final String ROUTE_EMPTY = "";
    public static final String ROUTE_BY_CAPABILITIES = "/byCapabilities";
    public static final String CAPABILITY_ID_PATH_VARIABLE = "capabilityId";
    public static final String ROUTE_TECHNOLOGY_LINK_CAPACITIES = "/link-capabilities/{" + CAPABILITY_ID_PATH_VARIABLE + "}";
    //Table
    //Technology
    public static final String TECHNOLOGY_TABLE_NAME = "technology";
    //CapabilityTechnology
    public static final String CAPABILITY_TECHNOLOGY_TABLE_NAME = "capability_technology";
    public static final String TECHNOLOGY_COLUMN_ID = "technology_id";
    //Technology validations
    public static final int MIN_CHARACTERS_NAME_TECHNOLOGY = 1;
    public static final int MAX_CHARACTERS_NAME_TECHNOLOGY = 50;
    public static final int MIN_CHARACTERS_DESCRIPTION_TECHNOLOGY = 1;
    public static final int MAX_CHARACTERS_DESCRIPTION_TECHNOLOGY = 90;

    public static final String EXCEPTION_TECHNOLOGY_NAME_NULL = "The technology name cannot be null";
    public static final String EXCEPTION_TECHNOLOGY_NAME_SIZE = "The technology name must be between 1 and 50 characters";
    public static final String EXCEPTION_TECHNOLOGY_DESCRIPTION_NULL = "The technology description cannot be null";
    public static final String EXCEPTION_TECHNOLOGY_DESCRIPTION_SIZE = "The technology description must be between 1 and 90 characters";

    //EXCEPTIONS
    public static final String TECHNOLOGY_ERROR = "Error on Technology - [ERROR]";


}
