package com.planetapi.model.swapi;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import static org.mockito.Mockito.mock;


public class PlanetsResultTest {

    private PlanetsResults planetsResults;

    @Before
    public void setUp(){

        planetsResults = new PlanetsResults();
    }

    @Test
    public void shouldSetPlanetResults(){

        List<Result> resultList = mock(List.class);

        planetsResults.setResults(resultList);

        assertThat(planetsResults.getResults(), is(resultList));
    }
}
