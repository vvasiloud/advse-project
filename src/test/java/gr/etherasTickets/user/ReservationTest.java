package gr.etherasTickets.user;

import org.junit.Assert;
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

import java.util.Date;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import gr.etherasTickets.EtherasTicketsApplication;
import gr.etherasTickets.flight.FlightRepository;
import gr.etherasTickets.logic.models.Flight;
import gr.etherasTickets.rest.resources.ReservationResource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = EtherasTicketsApplication.class)
@WebAppConfiguration
public class ReservationTest {

	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	@Autowired
	private FlightRepository flightrepository;
	
	@Autowired
	private UserRepository userRepository;

	@Before
	public void SetUp(){
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		
		flightrepository.deleteAll();
		userRepository.deleteAll();
	}
	
	@Test
	public void testAddReservation() throws Exception{
		Flight flight = flightrepository.save(new Flight("Thessaloniki", "Athens", 50, 60, new Date()));
		User user = userRepository.save(new User("Test" , "Testopoulos" ,"testopoulos@test.test" , "test1" , "password" , 200));
		
		ReservationResource reservationResoucre = new ReservationResource(flight.getId(), 3);
		
		mockMvc.perform(
				post(String.format("/users/%s/reservations" , user.getId()))
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.content(reservationResoucre.toJson()))
		.andExpect(status().isOk());
		
		user = userRepository.getUserById(user.getId());
		flight = flightrepository.getFlightById(flight.getId());
		
		//Check if user has 50 balance 200 - (50 * 3) = 200 - 150 = 50; 
		if(user.getBalance() != 50.0)
			fail("User balance must be 50");
		
		//Check if flights seat are 60 - 3 = 57
		assertEquals(57 , flight.getAvailableSeats());
	}

	
	@Test
	public void testRemoveReservation() throws Exception{
		Flight flight = flightrepository.save(new Flight("Patra", "Athens", 50, 60, new Date()));
		User user = new User("Test2" , "Testopoulos" ,"testopoulos2@test.test" , "test2" , "password" , 200);
		
		Reservation reservation = new Reservation(flight, 1 , new Date());
		
		user.addReservation(reservation);
		user = userRepository.save(user);
		
		mockMvc.perform(
				delete(String.format("/users/%s/reservations/%s" , user.getId() , reservation.getId())))
		.andExpect(status().isOk());
		
		user = userRepository.getUserById(user.getId());
		
		for(Reservation r : user.getReservations()){
			if(r.getId().equals(reservation.getId()))
				if(!r.isCancel())
					fail("Reservation cancel must be true");
				else
					return;
		}
		fail("Reservation is not into user");
	}
}

