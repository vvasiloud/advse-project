package gr.etherasTickets.user;

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
import gr.etherasTickets.rest.resources.ReservationResource;
import scala.annotation.meta.setter;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = EtherasTicketsApplication.class)
@WebAppConfiguration
public class ReservationTest {

	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	@Autowired
	private FlightRepository flightrepository;
	private Flight flight;
	
	@Autowired
	private UserRepository userRepository;
	private User user;

	@Before
	public void SetUp(){
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		
		flightrepository.deleteAll();
		flight = flightrepository.save(new Flight("Thessaloniki", "Athens", 50, 60, new Date()));
		
		userRepository.deleteAll();
		user = userRepository.save(new User("Test" , "Testopoulos" ,"testopoulos@test.test" , "test132" , "password" , 100));
		

	}
	
	@Test
	public void testAddReservation() throws Exception{
		ReservationResource reservation = new ReservationResource(flight.getId(), 3);
		
		mockMvc.perform(
				post(String.format("/users/%s/reservations" , user.getId()))
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.content(reservation.toJson()))
		.andExpect(status().isOk());
	}


}

