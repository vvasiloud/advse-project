package gr.etherasTickets.flight;

import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;


import gr.etherasTickets.EtherasTicketsApplication;
import gr.etherasTickets.exceptions.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = EtherasTicketsApplication.class, loader = AnnotationConfigContextLoader.class)
public class FlightTest {

	//Cities Thessaloniki  Ioannina  Athens  Patras Heraklion Rhodes  Larissa  Volos


	@Autowired
	private FlightRepository repository;

	
	
	@Before
	public void SetUp(){
		repository.deleteAll();
		repository.save(new Flight("Thessaloniki", "Athens", 50, 60, new Date()));
		repository.save(new Flight("Rhodes", "Athens",50, 40, new Date()));
		repository.save(new Flight("Ioannina", "Athens", 90, 60, new Date()));
		repository.save(new Flight("Thessaloniki", "Ioannina", 30, 60, new Date()));
		repository.save(new Flight("Athens", "Thessaloniki", 40, 60, new Date()));
		repository.save(new Flight("Thessaloniki", "Heraklion", 100, 60, new Date()));
	}
	
	@After
	public void Restore(){
		repository.deleteAll();
	}

	@Test
	public void testSearchFlightsTo() throws BadArguments, NotFound{
		List<Flight> flights = repository.searchFlights("Thessaloniki", null, -1, -1, -1);
		Assert.assertEquals(3 , flights.size());
	}
	
	@Test
	public void testSearchFlightsFrom() throws BadArguments, NotFound{
		List<Flight> flights = repository.searchFlights(null, "Athens", -1, -1, -1);
		Assert.assertEquals(3,flights.size());
	}
	
	@Test
	public void testSearchFlightsNotExistCity() throws BadArguments{
		try{
			List<Flight> flights = repository.searchFlights(null, "Rhodes", -1, -1, -1);
			Assert.fail("Rhodes does not exist at from");
		}catch(NotFound ex){
		}
	}
	
	@Test
	public void testSearchFlightsEqualPrice() throws BadArguments, NotFound{
		List<Flight> flights = repository.searchFlights(null, "Athens", -1, 50, 50);
		Assert.assertNotEquals(flights.size(), 1);
	}
	
	@Test
	public void testSearchFlightsMinPriceGtMaxPrice() throws NotFound{
		try{
			List<Flight> flights = repository.searchFlights(null, "Rhodes", -1, 50, 30);
			Assert.fail("minPrice > maxPrice must throw BadArguments");
		}catch(BadArguments ex){
		}
	}
	
	@Test
	public void testGetFlightById() throws BadArguments, NotFound{
		String  flightID = repository.findAll().get(0).getId();
		Flight flight = repository.getFlightById(flightID);
		Assert.assertEquals(flightID,flight.getId());
	}
	
	@Test
	public void testGetFlightByIdSeats() throws BadArguments, NotFound{
		Flight  flight = repository.findAll().get(0);
		List<Seat> seats = repository.getSeatsById(flight.getId());
		Assert.assertEquals(flight.getSeats().get(0).getCode(), seats.get(0).getCode());
	}

}
