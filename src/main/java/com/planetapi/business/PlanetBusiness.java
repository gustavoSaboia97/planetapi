package com.planetapi.business;

import com.planetapi.model.Planet;

import java.util.List;

public interface PlanetBusiness {

    Planet addPlanet(Planet planet);
    List<Planet> getPlanets();
    Planet getPlanetById(String id);
    Planet getPlanetByName(String name);
    void deletePlanetById(String id);
}
