package com.starwars.planetapi.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Planet {
	
	@Id
	private String planetId;
	private String name;
	private String climate;
	private String terrain;
	private Integer countFilms;
	
	public Planet() {
		
	}
	
	public Planet(String planetId, String name, String climate, String terrain, Integer countFilms) {
		this.planetId = planetId;
		this.name = name;
		this.climate = climate;
		this.terrain = terrain;
		this.countFilms = countFilms;
	}
	
	public Planet(String name, String climate, String terrain) {
		this.name = name;
		this.climate = climate;
		this.terrain = terrain;
	}
	
	public String getPlanetId() {
		return planetId;
	}
	public void setPlanetId(String planetId) {
		this.planetId = planetId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getClimate() {
		return climate;
	}
	public void setClimate(String climate) {
		this.climate = climate;
	}
	public String getTerrain() {
		return terrain;
	}
	public void setTerrain(String terrain) {
		this.terrain = terrain;
	}
	public Integer getCountFilms() {
		return countFilms;
	}
	public void setCountFilms(Integer countFilms) {
		this.countFilms = countFilms;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((planetId == null) ? 0 : planetId.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Planet other = (Planet) obj;
		if (planetId == null) {
			if (other.planetId != null)
				return false;
		} else if (!planetId.equals(other.planetId))
			return false;
		return true;
	}
	
	
	
}
