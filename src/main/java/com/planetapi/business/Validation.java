package com.planetapi.business;

import com.planetapi.model.Planet;

public interface Validation {

    void validateFields(Planet planet);
    void validateField(String fieldName, String fieldValue );
}
