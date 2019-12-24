package com.starwars.planetapi.dal;

import java.util.List;

import com.starwars.planetapi.model.Planet;

public interface PlanetDAL {

	List<Planet> getAllPlanets();

	Planet getPlanetById(String planetId);
	
	Planet getPlanetByName(String planetName);

	Planet addNewPlanet(Planet planet);
	
	Void removePlanet(Planet planet);
}