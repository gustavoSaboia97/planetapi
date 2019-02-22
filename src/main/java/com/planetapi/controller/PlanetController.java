package com.planetapi.controller;

import com.planetapi.business.PlanetBusiness;
import lombok.extern.slf4j.Slf4j;

import com.planetapi.model.Planet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import static org.springframework.http.ResponseEntity.status;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.HttpStatus.NO_CONTENT;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.List;

import static com.planetapi.components.UrlBuilder.*;

@Slf4j
@RestController
@RequestMapping(PLANET_API_PATH)
public class PlanetController {

    @Autowired
    private PlanetBusiness planetBusiness;

    @PostMapping
    public ResponseEntity<Planet> createPlanet(@RequestBody Planet planet){

        log.info("POST: Accessing {} to create new planet with following data {}", PLANET_API_PATH, planet);

        Planet insertedPlanet = planetBusiness.addPlanet(planet);

        return status(CREATED).body(insertedPlanet);
    }

    @GetMapping
    public ResponseEntity<List> getPlanets(){

        log.info("GET: Accessing {} to get all planets", PLANET_API_PATH);

        List<Planet> planets = planetBusiness.getPlanets();

        return status(OK).body(planets);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Planet> getPlanetById(@PathVariable String id){

        log.info("GET: Accessing {} to get a planet with id {}", PLANET_API_PATH, id);

        Planet planet = planetBusiness.getPlanetById(id);

        return status(OK).body(planet);
    }

    @GetMapping(PLANET_API_PATH_NAME)
    public ResponseEntity<Planet> getPlanetByName(@RequestParam("name") String name){

        log.info("GET: Accessing {} to get a planet with name {}", PLANET_API_PATH, name);

        Planet planet = planetBusiness.getPlanetByName(name);

        return status(OK).body(planet);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePlanetById(@PathVariable String id){

        log.info("DELETE: Accessing {} to DELETE a planet with id {}", PLANET_API_PATH, id);

        planetBusiness.deletePlanetById(id);

        return status(NO_CONTENT).build();
    }
}
