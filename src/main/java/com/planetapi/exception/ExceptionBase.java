package com.planetapi.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class ExceptionBase extends RuntimeException {

    @Getter
    private HttpStatus httpStatus;

    public ExceptionBase(HttpStatus httpStatus, String message){
        super(message);
        this.httpStatus = httpStatus;
    }
}
