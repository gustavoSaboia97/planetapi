package com.planetapi.business;

import com.planetapi.model.Planet;
import com.planetapi.repository.PlanetRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class PlanetBusinessImpl implements PlanetBusiness {

    @Autowired
    private PlanetRepository planetRepository;

    @Override
    public Planet addPlanet(Planet planet){

        log.info("Inserting planet {} in mongodb", planet);

        Planet insertedPlanet = planetRepository.insert(planet);

        return insertedPlanet;
    }

    @Override
    public List<Planet> getPlanets(){

        log.info("Getting all planets from database");

        List<Planet> planets = planetRepository.findAll();

        return planets;
    }

    @Override
    public Planet getPlanetById(String id){

        log.info("Getting planet with name {}", id);

        Optional<Planet> planetOptional = planetRepository.findById(id);

        return planetOptional.get();
    }

    @Override
    public Planet getPlanetByName(String name){

        log.info("Getting planet with name {}", name);

        Optional<Planet> planetOptional = planetRepository.findByName(name);

        return planetOptional.get();
    }

    @Override
    public void deletePlanetById(String id){

        log.info("Deleting planet with id {}", id);

        planetRepository.deleteById(id);
    }
}
