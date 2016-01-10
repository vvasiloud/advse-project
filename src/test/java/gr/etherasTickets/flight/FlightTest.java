package gr.etherasTickets.flight;

import java.util.Date;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import gr.etherasTickets.EtherasTicketsApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = EtherasTicketsApplication.class, loader = AnnotationConfigContextLoader.class)
@WebAppConfiguration
public class FlightTest {
	
	private final String citiesPool[] = {"Thessaloniki" , "Ioannina" , "Athens" , "Patras" , "Heraklion" , "Rhodes" , "Larissa" , "Volos"};
	private final Integer pricePool[] = {20 , 30 , 50 , 80 , 100 , 150 , 200 , 300};
	private final Integer seatPool[] = {40 , 50 , 60 , 70 , 100};
	private final Random random = new Random();
	
	private MockMvc mockMvc;
	
	@Autowired
    private WebApplicationContext webApplicationContext;
	
	@Autowired
	private FlightRepository repository;
	
	@Before
	public void SetUp(){
		 mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	@Before
	public void setUpRandomFlights(){
		int flightsSize = 50;
		Flight[] flights = new Flight[flightsSize];
		
		for(int i = 0 ; i< flights.length;i++){
			String from = getRandomCity();
			String to = getRandomCity(from);
			
			flights[i] = new Flight(from , to , getRandomPrice() , getRandomMaxSeat() , getRandomDate());
		}
		
		repository.deleteAll();
		for(int i = 0 ; i< flights.length;i++){
			repository.save(flights[i]);
		}
	}
	
	@Test 
	public void testMinMaxFlightArguments()throws Exception{
		
			mockMvc.perform(get("/flights").param("maxPrice", "0").param("minPrice","0"))
			.andExpect(status().isOk());
								
			mockMvc.perform(get("/flights").param("maxPrice", "50").param("minPrice","50"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$[0].price", is(50)));
			
			mockMvc.perform(get("/flights").param("maxPrice", "30").param("minPrice","50"))
			.andExpect(status().isBadRequest());
			
			mockMvc.perform(get("/flights").param("maxPrice", "50").param("minPrice","30"))
			.andExpect(status().isOk())
			.andExpect((ResultMatcher) jsonPath("$[0]" , Criteria.where("price").gte("50").and("price").lte("30")));
			
		
		
		
		
		
	}
	
	
	
	Date getRandomDate(){
		return new Date(System.currentTimeMillis() + random.nextLong());
	}
	
	int getRandomMaxSeat(){
		return seatPool[random.nextInt(seatPool.length)];
	}
	
	int getRandomPrice(){
		return pricePool[random.nextInt(pricePool.length)];
	}
	
	String getRandomCity(String notEqual){
		int index = random.nextInt(citiesPool.length);
		String temp = citiesPool[index];
		if(notEqual.equals(temp))
			temp = citiesPool[(index % (citiesPool.length - 1)) + 1];
		return temp;
	}
	
	String getRandomCity(){
		return citiesPool[random.nextInt(citiesPool.length)];
	}
}
