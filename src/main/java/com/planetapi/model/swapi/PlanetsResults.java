package com.planetapi.model.swapi;

import lombok.Data;

import java.util.List;

@Data
public class PlanetsResults {

    private List<Result> results;
}
