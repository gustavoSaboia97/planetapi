package com.planetapi.model.swapi;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;

public class ResultTest {

    private Result result;

    @Before
    public void setUp(){

        result = new Result();
    }

    @Test
    public void shouldSetResult(){

        List<String> films = mock(List.class);

        result.setFilms(films);

        assertThat(result.getFilms(), is(films));
    }
}
