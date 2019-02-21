package com.planetapi.business;

import com.planetapi.model.Planet;

import java.util.List;

public interface PlanetBusiness {

    public Planet addPlanet(Planet planet);
    public List<Planet> getPlanets();
    public Planet getPlanetById(String id);
    public Planet getPlanetByName(String name);
    public void deletePlanetById(String id);
}
