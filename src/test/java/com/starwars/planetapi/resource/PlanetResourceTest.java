package com.starwars.planetapi.resource;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.starwars.planetapi.model.Planet;

@RunWith(SpringRunner.class)
@WebMvcTest(value = PlanetResource.class, secure = false)
public class PlanetResourceTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private PlanetResource planetResource;
		
	Planet planetMock = new Planet("1", "Terra1", "Arido", "Deserto", 2);
	List<Planet> mockPlanets = Arrays.asList(new Planet("1", "Terra1", "Arido", "Deserto", 2));
	
	
	String examplePlanet = "{\"name\":\"Yavin IV\",\"climate\":\"Arido\",\"terrain\":\"Deserto\"}";
	
	@Test
	public void getPlanetList() throws Exception {
		Mockito.when(
				planetResource.getAllPlanets()
				).thenReturn(mockPlanets);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/planet/findAll").accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expected = "[{\"planetId\":\"1\",\"name\":\"Terra1\",\"climate\":\"Arido\",\"terrain\":\"Deserto\",\"countFilms\":2}]";
		
		System.out.println(result.getResponse());
		JSONAssert.assertEquals(expected, result.getResponse()
				.getContentAsString(), false);
		
	}
}
