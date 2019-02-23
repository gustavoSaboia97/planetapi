package com.planetapi.business;

import com.planetapi.exception.CannotBeBlankException;
import com.planetapi.exception.PlanetAlreadyExistsException;
import com.planetapi.model.Planet;
import com.planetapi.repository.PlanetRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest({Optional.class})
public class PlanetBusinessImplTest {

    @InjectMocks
    private PlanetBusinessImpl planetBusinessImpl;

    @Mock
    private PlanetRepository planetRepository;
    @Mock
    private Validation validation;

    private Planet planetMock;

    @Before
    public void setUp(){

        planetMock = mock(Planet.class);
    }

    @Test
    public void shouldAddNewPlanet(){

        String name = "name";

        Optional<Planet> optionalPlanet = PowerMockito.mock(Optional.class);
        Planet insertedPlanet = mock(Planet.class);

        when(planetMock.getName()).thenReturn(name);
        when(planetRepository.findByName(name)).thenReturn(optionalPlanet);
        when(optionalPlanet.isPresent()).thenReturn(false);
        PowerMockito.when(planetRepository.insert(planetMock)).thenReturn(insertedPlanet);

        Planet newPlanet = planetBusinessImpl.addPlanet(planetMock);

        verify(validation).validateFields(planetMock);
        verify(planetRepository).findByName(name);
        verify(planetRepository).insert(planetMock);

        assertThat(newPlanet, is(insertedPlanet));
    }

    @Test(expected = PlanetAlreadyExistsException.class)
    public void shouldRaizeThatPlanetExists(){

        String name = "name";

        Optional<Planet> optionalPlanet = Optional.of(planetMock);

        when(planetMock.getName()).thenReturn(name);
        when(planetRepository.findByName(name)).thenReturn(optionalPlanet);

        planetBusinessImpl.addPlanet(planetMock);

        verify(validation).validateFields(planetMock);
        verify(planetRepository).findByName(name);
        verify(planetRepository, times(0)).insert(planetMock);
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
    public void shouldGetPlanetById(){

        String id = "id";

        Optional<Planet> optionalPlanet = Optional.of(planetMock);

        when(planetRepository.findById(id)).thenReturn(optionalPlanet);

        Planet receivedPlanet = planetBusinessImpl.getPlanetById(id);

        verify(planetRepository).findById(id);

        assertThat(receivedPlanet, is(planetMock));
    }

    @Test
    public void shouldGetPlanetByName(){

        String name = "name";

        Optional<Planet> optionalPlanet = Optional.of(planetMock);

        when(planetRepository.findByName(name)).thenReturn(optionalPlanet);

        Planet receivedPlanet = planetBusinessImpl.getPlanetByName(name);

        verify(planetRepository).findByName(name);

        assertThat(receivedPlanet, is(planetMock));
    }

    @Test
    public void shouldDeletePlanetById(){

        String id = "id";

        planetBusinessImpl.deletePlanetById(id);

        verify(planetRepository).deleteById(id);
    }
}
