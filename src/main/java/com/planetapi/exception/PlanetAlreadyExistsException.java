package com.planetapi.exception;

import static org.springframework.http.HttpStatus.CONFLICT;

public class PlanetAlreadyExistsException extends ExceptionBase {

    public PlanetAlreadyExistsException(){
        super(CONFLICT, "Planet Already Exists");
    }
}
