package com.planetapi.business;

import com.planetapi.exception.PlanetAlreadyExistsException;
import com.planetapi.exception.PlanetNotFoundException;
import com.planetapi.model.Planet;
import com.planetapi.repository.PlanetRepository;
import com.planetapi.service.SwapiRequestService;
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
    @Autowired
    private Validation validation;
    @Autowired
    private SwapiRequestService swapiRequestService;

    @Override
    public Planet addPlanet(Planet planet){

        log.info("Inserting planet {} in mongodb", planet);

        validation.validateFields(planet);

        Optional<Planet> optionalPlanet = planetRepository.findByName(planet.getName());

        if (optionalPlanet.isPresent())
            throw new PlanetAlreadyExistsException();

        Planet insertedPlanet = planetRepository.insert(planet);

        insertedPlanet.setApparitionCounter(swapiRequestService.getApparisons(insertedPlanet.getName()));

        return insertedPlanet;
    }

    @Override
    public List<Planet> getPlanets(){

        log.info("Getting all planets from database");

        List<Planet> planets = planetRepository.findAll();

        planets.forEach(planet -> planet.setApparitionCounter(swapiRequestService.getApparisons(planet.getName())));

        return planets;
    }

    @Override
    public Planet getPlanetById(String id){

        log.info("Getting planet with name {}", id);

        validation.validateField("id", id);

        Optional<Planet> planetOptional = planetRepository.findById(id);

        if (!planetOptional.isPresent())
            throw new PlanetNotFoundException();

        Planet planet = planetOptional.get();
        planet.setApparitionCounter(swapiRequestService.getApparisons(planet.getName()));

        return planetOptional.get();
    }

    @Override
    public Planet getPlanetByName(String name){

        log.info("Getting planet with name {}", name);

        validation.validateField("name", name);

        Optional<Planet> planetOptional = planetRepository.findByName(name);

        if (!planetOptional.isPresent())
            throw new PlanetNotFoundException();

        Planet planet = planetOptional.get();
        planet.setApparitionCounter(swapiRequestService.getApparisons(planet.getName()));

        return planet;
    }

    @Override
    public void deletePlanetById(String id){

        log.info("Deleting planet with id {}", id);

        Optional<Planet> planetOptional = planetRepository.findById(id);

        if (!planetOptional.isPresent())
            throw new PlanetNotFoundException();

        planetRepository.deleteById(id);
    }
}
