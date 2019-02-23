package com.planetapi.model;

import lombok.Getter;

public class Error {

    @Getter
    private String error;

    public Error(String error){
        this.error = error;
    }
}
