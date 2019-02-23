package com.planetapi.business;

import com.planetapi.model.Planet;

public interface Validation {

    public void validateFields(Planet planet);
    public void validateField(String fieldName, String fieldValue );
}
