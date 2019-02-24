package com.planetapi.service;

import com.planetapi.components.SwapiComponent;

import com.planetapi.model.swapi.PlanetsResults;
import com.planetapi.model.swapi.Result;
import org.junit.Before;
import org.junit.Test;

import org.junit.runner.RunWith;

import org.mockito.*;

import org.mockito.junit.MockitoJUnitRunner;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SwapiRequestServiceImplTest {

    @InjectMocks
    private SwapiRequestServiceImpl swapiRequestService;

    @Mock
    private SwapiComponent swapiComponent;

    @Mock
    private RestTemplate restTemplate;
    @Mock
    private ResponseEntity responseEntity;
    @Mock
    private PlanetsResults planetsResults;
    @Mock
    private List<Result> results;
    @Mock
    private Result result;
    @Mock
    private List<String> films;

    private String name;
    private String url;

    @Before
    public void setUp() throws Exception {

        url = "https://swapi.co/api/planets/";

        when(swapiComponent.getUrl()).thenReturn(url);

        when(restTemplate.exchange(ArgumentMatchers.anyString(),
                ArgumentMatchers.any(HttpMethod.class),
                ArgumentMatchers.<HttpEntity<?>> any(),
                ArgumentMatchers.<Class<String>> any())).thenReturn(responseEntity);

        when(responseEntity.getBody()).thenReturn(planetsResults);
        when(planetsResults.getResults()).thenReturn(results);
    }

    @Test
    public void shouldGetZeroApparisons() {

        name = "name";

        when(results.isEmpty()).thenReturn(true);

        int apparitionCounter = swapiRequestService.getApparisons(name);

        assertThat(apparitionCounter, is(0));
    }

    @Test
    public void shouldGetApparisons() {

        name = "name";

        when(results.isEmpty()).thenReturn(false);
        when(results.get(0)).thenReturn(result);
        when(result.getFilms()).thenReturn(films);
        when(films.size()).thenReturn(1);

        int apparitionCounter = swapiRequestService.getApparisons(name);

        assertThat(apparitionCounter, is(1));
    }
}
