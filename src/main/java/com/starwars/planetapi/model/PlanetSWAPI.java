package com.starwars.planetapi.model;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PlanetSWAPI {
	
	private String name;
	private ArrayList<String> films;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<String> getFilms() {
		return films;
	}
	public void setFilms(ArrayList<String> films) {
		this.films = films;
	}
	
	
	
	
	
}
