package com.planetapi.model;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ErrorTest {

    private Error error;

    @Test
    public void shouldReturnMessageThatWasSet(){

        String message = "message";

        error = new Error(message);

        assertThat(message, is(error.getError()));
    }
}
