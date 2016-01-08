package gr.etherasTickets.flight;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.ws.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;


@RestController
@RequestMapping("/flights")
public class FlightController {
	
	@Autowired
	private FlightRepository repository;
	
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Flight>> getFlights(
			@RequestParam(name="to" , required=false) String to,
			@RequestParam(name="from" , required=false) String from,
			@RequestParam(name="availableSeats" , required=false) String availableSeats,
			@RequestParam(name="maxPrice" , required=false) String maxPrice,
			@RequestParam(name="minPrice" , required=false) String minPrice
	){
		return new ResponseEntity<List<Flight>>( repository.searchFlights(to, from, availableSeats, maxPrice, minPrice), HttpStatus.OK);
	}
	
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity createFlight(@RequestBody Flight newFlight){
		repository.save(newFlight);
		return new ResponseEntity(HttpStatus.OK);
	}
	
	
}
