package com.planetapi.controller;

import com.planetapi.exception.ExceptionBase;
import com.planetapi.model.Error;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.ResponseEntity.status;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Slf4j
@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler({ExceptionBase.class})
    public ResponseEntity<Error> knownExceptionHandler(ExceptionBase ex){

        log.error("Known error:  {}", ex.getMessage());

        Error error = new Error(ex.getMessage());

        return status(ex.getHttpStatus()).body(error);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<Error> parameterExceptionHandler(MethodArgumentNotValidException ex){

        log.error("Known error:  {}", ex.getMessage());

        Error error = new Error(ex.getMessage());

        return status(BAD_REQUEST).body(error);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Error> genericExceptionHandler(Exception ex){

        log.error("Unknown error: {}", ex.getMessage());

        Error error = new Error(ex.getMessage());

        return status(INTERNAL_SERVER_ERROR).body(error);
    }
}
