package com.starwars.planetapi.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.starwars.planetapi.model.Planet;

@Repository
public interface PlanetRepository extends MongoRepository<Planet, String> {
}
