package com.planetapi.business;

import com.planetapi.model.Planet;
import com.planetapi.repository.PlanetRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PlanetBusinessImplTest {

    @InjectMocks
    private PlanetBusinessImpl planetBusinessImpl;

    @Mock
    private PlanetRepository planetRepository;

    private Planet planetMock;

    @Before
    public void setUp(){

        planetMock = mock(Planet.class);
    }

    @Test
    public void shouldCreateNewPlanet(){

        Planet newPlanetMock = mock(Planet.class);

        when(planetRepository.insert(planetMock)).thenReturn(newPlanetMock);

        Planet newPlanet = planetBusinessImpl.addPlanet(planetMock);

        verify(planetRepository).insert(planetMock);

        assertThat(newPlanet, is(newPlanetMock));
    }

    @Test
    public void shouldGetAllPlanets(){

        List<Planet> planetsMock = mock(List.class);

        when(planetRepository.findAll()).thenReturn(planetsMock);

        List<Planet> planets = planetBusinessImpl.getPlanets();

        verify(planetRepository).findAll();

        assertThat(planets, is(planetsMock));
    }

    @Test
    public void shouldDeletePlanetById(){

        String id = "id";

        planetBusinessImpl.deletePlanetById(id);

        verify(planetRepository).deleteById(id);
    }
}
