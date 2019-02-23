package com.planetapi.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

public class CannotBeBlankException extends ExceptionBase {

    public CannotBeBlankException(String field){ super(BAD_REQUEST, "Field " + field + " Cannot be blank"); }
}
