package gr.etherasTickets.flight;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.jayway.jsonpath.JsonPath;

import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import gr.etherasTickets.EtherasTicketsApplication;
import gr.etherasTickets.flight.FlightRepository;
import gr.etherasTickets.logic.models.Flight;
import gr.etherasTickets.user.User;
import gr.etherasTickets.user.UserRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = EtherasTicketsApplication.class)
@WebAppConfiguration
public class FlightTest {

	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	@Autowired
	private FlightRepository repository;
	
	
	private Flight firstFlight;

	@Before
	public void SetUp(){
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		
		repository.deleteAll();
		
		firstFlight = repository.save(new Flight("Thessaloniki", "Athens", 50, 60, new Date()));
		repository.save(new Flight("Rhodes", "Athens",50, 40, new Date()));
		repository.save(new Flight("Ioannina", "Athens", 90, 60, new Date()));
		repository.save(new Flight("Thessaloniki", "Ioannina", 30, 60, new Date()));
		repository.save(new Flight("Athens", "Thessaloniki", 40, 60, new Date()));
		repository.save(new Flight("Thessaloniki", "Heraklion", 100, 60, new Date()));
	}
	
	
	@Test
	public void testGetFlighs() throws Exception{
		mockMvc.perform(get("/flights"))
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
		.andExpect(jsonPath("$").isArray())
		.andExpect(jsonPath("$[0].id").value(firstFlight.getId()));
    }



	@Test
	public void testSearchFlightsTo() throws Exception{
		String testTo = "Thessaloniki";
		mockMvc.perform(get(String.format("/flights?to=%s", testTo)))
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
		.andExpect(jsonPath("$").isArray())
		.andExpect(jsonPath("$[0].to").value(testTo));
	}

	@Test
	public void testSearchFlightsFrom() throws Exception{
		String testFrom = "Athens";
		mockMvc.perform(get(String.format("/flights?from=%s", testFrom)))
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
		.andExpect(jsonPath("$").isArray())
		.andExpect(jsonPath("$[0].from").value(testFrom));
	}

	@Test
	public void testSearchFlightsNotExistCity() throws Exception{
		String testFrom = "asfgas2gags";
		mockMvc.perform(get(String.format("/flights?from=%s", testFrom)))
		.andExpect(status().isNotFound());
	}


	public void testSearchFlightsEqualPrice() throws Exception{
		String price = "50";
		mockMvc.perform(get(String.format("/flights?minPrice=%s&maxPrice=%s", price , price)))
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
		.andExpect(jsonPath("$").isArray())
		.andExpect(jsonPath("$[0].price").value(price))
		.andExpect(jsonPath("$[1].price").value(price));
	}

	@Test
	public void testSearchFlightsMinPriceGtMaxPrice() throws Exception{
		mockMvc.perform(get(String.format("/flights?minPrice=%s&maxPrice=%s", "50" , "30")))
		.andExpect(status().isBadRequest());
	}

	@Test
	public void testGetFlightById() throws Exception{
		mockMvc.perform(get(String.format("/flights/%s", firstFlight.getId())))
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
		.andExpect(jsonPath("$.id").value(firstFlight.getId()));
	}

	/*
	@Test
	public void testGetFlightByIdSeats() throws Exception{
		mockMvc.perform(get(String.format("/flights/%s/seats" , firstFlight.getId())))
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
		.andExpect(jsonPath("$").isArray());
	}
	*/



}

