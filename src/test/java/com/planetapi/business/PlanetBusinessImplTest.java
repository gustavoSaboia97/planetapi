package com.planetapi.business;

import com.planetapi.exception.PlanetAlreadyExistsException;
import com.planetapi.exception.PlanetNotFoundException;
import com.planetapi.model.Planet;
import com.planetapi.repository.PlanetRepository;
import com.planetapi.service.SwapiRequestService;
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
    @Mock
    private SwapiRequestService swapiRequestService;

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
        when(planetRepository.insert(planetMock)).thenReturn(insertedPlanet);
        when(insertedPlanet.getName()).thenReturn(name);

        Planet newPlanet = planetBusinessImpl.addPlanet(planetMock);

        verify(planetRepository).findByName(name);
        verify(planetRepository).insert(planetMock);
        verify(swapiRequestService).getApparisons(name);

        assertThat(newPlanet, is(insertedPlanet));
    }

    @Test(expected = PlanetAlreadyExistsException.class)
    public void shouldRaizeThatPlanetExists(){

        String name = "name";

        Optional<Planet> optionalPlanet = Optional.of(planetMock);

        when(planetMock.getName()).thenReturn(name);
        when(planetRepository.findByName(name)).thenReturn(optionalPlanet);

        planetBusinessImpl.addPlanet(planetMock);

        verify(planetRepository).findByName(name);
        verify(planetRepository, times(0)).insert(planetMock);
    }

    @Test
    public void shouldGetAllPlanets(){

        String name = "name";

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

        verify(validation).validateField("id", id);
        verify(planetRepository).findById(id);

        assertThat(receivedPlanet, is(planetMock));
    }

    @Test(expected = PlanetNotFoundException.class)
    public void shouldRaizesPlanetNotFoundById(){

        String id = "id";

        planetBusinessImpl.getPlanetById(id);

        verify(validation).validateField("id", id);
        verify(planetRepository).findById(id);
    }

    @Test
    public void shouldGetPlanetByName(){

        String name = "name";

        Optional<Planet> optionalPlanet = Optional.of(planetMock);

        when(planetRepository.findByName(name)).thenReturn(optionalPlanet);

        Planet receivedPlanet = planetBusinessImpl.getPlanetByName(name);

        verify(validation).validateField("name", name);
        verify(planetRepository).findByName(name);

        assertThat(receivedPlanet, is(planetMock));
    }

    @Test(expected = PlanetNotFoundException.class)
    public void shouldRaizesPlanetNotFoundByName(){

        String name = "name";

        planetBusinessImpl.getPlanetByName(name);

        verify(validation).validateField("name", name);
        verify(planetRepository).findByName(name);
    }

    @Test
    public void shouldDeletePlanetById(){

        String id = "id";

        Optional<Planet> optionalPlanet = Optional.of(planetMock);

        when(planetRepository.findById(id)).thenReturn(optionalPlanet);

        planetBusinessImpl.deletePlanetById(id);

        verify(planetRepository).findById(id);
        verify(planetRepository).deleteById(id);
    }

    @Test(expected = PlanetNotFoundException.class)
    public void shouldRaizesPlanetNotFoundDeletingById(){

        String id = "id";

        planetBusinessImpl.deletePlanetById(id);

        verify(planetRepository).findById(id);
        verify(planetRepository, times(0)).deleteById(id);
    }
}
