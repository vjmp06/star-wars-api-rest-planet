package com.starwars.planetapi.resource;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.starwars.planetapi.dal.PlanetDAL;
import com.starwars.planetapi.exception.PlanetNotFoundException;
import com.starwars.planetapi.model.Planet;
import com.starwars.planetapi.model.SWAPI;
import com.starwars.planetapi.repository.PlanetRepository;

@RestController
@RequestMapping(value = "/planet")
public class PlanetResource {

	private final Logger LOG = LoggerFactory.getLogger(getClass());

	private final PlanetRepository planetRepository;

	private final PlanetDAL planetDAL;
	
	@Autowired
	private RestTemplate templateRest;
	
	@Value("${swapi.uri}")
	private String swapiUri;

	public PlanetResource(PlanetRepository planetRepository, PlanetDAL planetDAL) {
		this.planetRepository = planetRepository;
		this.planetDAL = planetDAL;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResponseEntity<Planet> addNewPlanet(@RequestBody Planet planet) {
		LOG.info("Saving planet.");
		Integer countFilms  = this.consumoServiceRest(planet.getName());
		planet.setCountFilms(countFilms);
		Planet result = planetRepository.save(planet);
		if(result == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(result);
	}

	@RequestMapping(value = "/findAll", method = RequestMethod.GET)
	public List<Planet> getAllPlanets() {
		LOG.info("Getting all planets.");
		return planetRepository.findAll();
	}

	
	@RequestMapping(value = "/{planetId}", method = RequestMethod.GET)
	public ResponseEntity<Planet> getPlanet(@PathVariable String planetId) {
			Planet result = planetRepository.findOne(planetId);
			if(result == null) {
				return ResponseEntity.notFound().build();
			}
			LOG.info("Getting planet with ID: {}.", planetId);
			return ResponseEntity.ok(result);
		
	}
	
	@RequestMapping(value = "/remove/{planetId}", method = RequestMethod.DELETE)
	public void removePlanet(@PathVariable String planetId) throws PlanetNotFoundException {
				ResponseEntity<Planet> existPlanet = this.getPlanet(planetId);
				if (!existPlanet.hasBody()) {
					throw new PlanetNotFoundException();
				}
				LOG.info("Remove planet with ID: {}.", planetId);
				planetRepository.delete(planetId);
				
			
			
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<Planet> findPlanetByName(@RequestParam String planetName) {
		Planet result = planetRepository.findOne(planetName);
		if(result == null) {
			return ResponseEntity.notFound().build();
		}
		LOG.info("Getting planet with name: {}.", planetName);
		return ResponseEntity.ok(result);
	}
	
	@RequestMapping(value = "/consumoRest", method = RequestMethod.GET)
	public Integer consumoServiceRest(String planetName) {
		 HttpHeaders headers = new HttpHeaders();
         headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
         headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
         HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
         SWAPI swapi = templateRest.exchange("https://swapi.co/api/planets?search={planet}", HttpMethod.GET, entity, SWAPI.class, planetName).getBody();
         
		return swapi.getResults().get(0).getFilms().size(); 
	}

}