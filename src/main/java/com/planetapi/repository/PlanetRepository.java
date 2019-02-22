package com.planetapi.repository;

import com.planetapi.model.Planet;

import org.springframework.data.mongodb.repository.MongoRepository;

import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface PlanetRepository extends MongoRepository<Planet, String> {

    Optional<Planet> findByName(String name);
    void deleteById(String id);
}
