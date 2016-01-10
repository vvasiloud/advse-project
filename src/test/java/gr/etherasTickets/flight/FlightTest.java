package gr.etherasTickets.flight;

import java.util.Date;
import java.util.List;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;


import gr.etherasTickets.EtherasTicketsApplication;
import gr.etherasTickets.exceptions.BadArguments;

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
	
	
	@Test 
	public void testMinMaxPriceFlightArguments() throws Exception{
		List<Flight> flights;
		
		//Equal price Test
		flights = repository.searchFlights(null, null, -1, 50, 50);
		if(flights.isEmpty())
			throw new Exception("Test minPrice 50 and maxPrice 50 is empty");
		if(!(flights.get(0).getPrice() == 50 && flights.get(1).getPrice() == 50))
			throw new Exception("Test minPrice 50 and maxPrice 50 price is not equal to 50");
		
		
		flights = repository.searchFlights(null, null, -1, 30, 50);
		if(flights.size() != 4 )
			throw new Exception("Test minPrice 30 and maxPrice 50 is not 4");
		
		try{
			flights = repository.searchFlights(null, null, -1, 50, 30);
			throw new Exception("Test minPrice 50 and maxPrice 30 does not throw BadArguments Exception");
		}catch(BadArguments ex){
			
		}
		
		flights = repository.searchFlights(null, null, -1, 0, 0);
		if(flights.isEmpty())
			throw new Exception("Test minPrice 0 and maxPrice 0 is empty");
	}
	
	
}
