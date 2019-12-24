package com.starwars.planetapi.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.starwars.planetapi.dal.PlanetDAL;
import com.starwars.planetapi.model.Planet;

@Repository
public class PlanetDALImpl implements PlanetDAL {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public List<Planet> getAllPlanets() {
		return mongoTemplate.findAll(Planet.class);
	}

	@Override
	public Planet getPlanetById(String planetId) {
		Query query = new Query();
		query.addCriteria(Criteria.where("planetId").is(planetId));
		return mongoTemplate.findOne(query, Planet.class);
	}
	
	@Override
	public Planet addNewPlanet(Planet Planet) {
		mongoTemplate.save(Planet);
		// Now, Planet object will contain the ID as well
		return Planet;
	}

	@Override
	public Void removePlanet(Planet planetId) {
		mongoTemplate.remove(Criteria.where("planetId").is(planetId));
		return null;
	}

	@Override
	public Planet getPlanetByName(String planetName) {
		return mongoTemplate.findById(Criteria.where("planetId").is(planetName), Planet.class);
		
	}

	
	
	
	
	

}
