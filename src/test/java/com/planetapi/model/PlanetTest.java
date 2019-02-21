package com.planetapi.model;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class PlanetTest {

    private Planet planet;

    @Before
    public void setUp(){

        planet = new Planet();
    }

    @Test
    public void shouldSetPlanetDataCorrectly(){

        String id = "planet_id";
        String name = "planet_id";
        String climate = "planet_climate";
        String terrain = "planet_terrain";

        planet.setId(id);
        planet.setName(name);
        planet.setClimate(climate);
        planet.setTerrain(terrain);

        assertThat(planet.getId(), is(id));
        assertThat(planet.getName(), is(name));
        assertThat(planet.getClimate(), is(climate));
        assertThat(planet.getTerrain(), is(terrain));
    }
}
