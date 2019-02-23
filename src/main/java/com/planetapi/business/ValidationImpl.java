package com.planetapi.business;

import com.planetapi.exception.CannotBeBlankException;

import com.planetapi.model.Planet;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import org.springframework.util.StringUtils;

@Slf4j
@Service
public class ValidationImpl implements Validation{

    @Override
    public void validateFields(Planet planet) {

        if ( planet == null )
            throw new CannotBeBlankException("planet");

        validateField("name", planet.getName());
        validateField("terrain", planet.getTerrain());
        validateField("climate", planet.getClimate());
    }

    @Override
    public void validateField(String fieldName, String fieldValue) {

        if (StringUtils.isEmpty(fieldValue))
            throw new CannotBeBlankException(fieldName);
    }
}
