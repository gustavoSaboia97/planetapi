package com.planetapi.exception;

import static org.springframework.http.HttpStatus.NOT_FOUND;

public class PlanetNotFoundException extends ExceptionBase{

    public PlanetNotFoundException(){
        super(NOT_FOUND, "Planet not found");
    }
}
