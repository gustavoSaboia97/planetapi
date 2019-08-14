package com.planetapi.controller;

import com.planetapi.model.Error;

import com.planetapi.exception.ExceptionBase;

import org.junit.Before;
import org.junit.Test;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;

public class ExceptionHandlerControllerTest {

    private ExceptionHandlerController exceptionHandlerController;

    @Before
    public void setUp(){
        exceptionHandlerController = new ExceptionHandlerController();
    }

    @Test
    public void shouldCreateKnownErrorMessage(){

        String message = "message";

        Error error = new Error(message);

        ExceptionBase exceptionBase = new ExceptionBase(HttpStatus.NOT_FOUND, message);

        ResponseEntity<Error> responseEntity = exceptionHandlerController.knownExceptionHandler(exceptionBase);

        assertThat( responseEntity.getBody().getError(), is(error.getError()));
        assertThat( responseEntity.getStatusCodeValue(), is(404));
    }

    @Test
    public void shouldCreateParameterErrorMessage(){

        String message = "message";

        Error error = new Error(message);

        MethodArgumentNotValidException exception = mock(MethodArgumentNotValidException.class);

        when(exception.getMessage()).thenReturn(message);

        ResponseEntity<Error> responseEntity = exceptionHandlerController.parameterExceptionHandler(exception);

        assertThat( responseEntity.getBody().getError(), is(error.getError()));
        assertThat( responseEntity.getStatusCodeValue(), is(400));
    }

    @Test
    public void shouldCreateGenericErrorMessage(){
        String message = "message";

        Error error = new Error(message);

        Exception exception = new Exception(message);

        ResponseEntity<Error> responseEntity = exceptionHandlerController.genericExceptionHandler(exception);

        assertThat( responseEntity.getBody().getError(), is(error.getError()));
        assertThat( responseEntity.getStatusCodeValue(), is(500));
    }
}
