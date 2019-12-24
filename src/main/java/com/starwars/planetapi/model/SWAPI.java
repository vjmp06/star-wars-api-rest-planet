package com.starwars.planetapi.model;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SWAPI {
	private ArrayList<PlanetSWAPI> results;

	public ArrayList<PlanetSWAPI> getResults() {
		return results;
	}

	public void setResults(ArrayList<PlanetSWAPI> results) {
		this.results = results;
	}
	
	
	
	
}
