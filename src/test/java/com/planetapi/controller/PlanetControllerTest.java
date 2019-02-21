package com.planetapi.controller;

import com.planetapi.business.PlanetBusiness;
import com.planetapi.model.Planet;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PlanetControllerTest {

    @InjectMocks
    private PlanetController planetController;

    @Mock
    private PlanetBusiness planetBusinessMock;

    private Planet planetMock;

    @Before
    public void setUp(){

        planetMock = mock(Planet.class);
    }

    @Test
    public void shouldCreateAPlanet(){

        Planet newPlanetMock = mock(Planet.class);

        when(planetBusinessMock.addPlanet(planetMock)).thenReturn(newPlanetMock);

        ResponseEntity<Planet> responseEntity = planetController.createPlanet(planetMock);

        verify(planetBusinessMock).addPlanet(planetMock);

        assertThat(responseEntity.getStatusCodeValue(), is(201));
        assertThat(responseEntity.getBody(), is(newPlanetMock));
    }

    @Test
    public void shouldGetAllPlanets(){

        List planetsMock = mock(List.class);

        when(planetBusinessMock.getPlanets()).thenReturn(planetsMock);

        ResponseEntity<List> responseEntity = planetController.getPlanets();

        verify(planetBusinessMock).getPlanets();

        assertThat(responseEntity.getStatusCodeValue(), is(200));
        assertThat(responseEntity.getBody(), is(planetsMock));
    }

    @Test
    public void shouldGetPlanetById(){

        String id = "id";

        when(planetBusinessMock.getPlanetById(id)).thenReturn(planetMock);

        ResponseEntity<Planet> responseEntity = planetController.getPlanetById(id);

        verify(planetBusinessMock).getPlanetById(id);

        assertThat(responseEntity.getStatusCodeValue(), is(200));
        assertThat(responseEntity.getBody(), is(planetMock));
    }

    @Test
    public void shouldGetPlanetByName(){

        String name = "name";

        when(planetBusinessMock.getPlanetByName(name)).thenReturn(planetMock);

        ResponseEntity<Planet> responseEntity = planetController.getPlanetByName(name);

        verify(planetBusinessMock).getPlanetByName(name);

        assertThat(responseEntity.getStatusCodeValue(), is(200));
        assertThat(responseEntity.getBody(), is(planetMock));
    }

    @Test
    public void shouldDeletePlanetById(){

        String id = "id";

        ResponseEntity<?> responseEntity = planetController.deletePlanetById(id);

        verify(planetBusinessMock).deletePlanetById(id);

        assertThat(responseEntity.getStatusCodeValue(), is(204));
    }
}
