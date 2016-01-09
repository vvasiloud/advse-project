package gr.etherasTickets.flight;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/flights")
public class FlightController {
	
	@Autowired
	private FlightRepository repository;
	
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Flight>> getFlights(
<<<<<<< HEAD
			@RequestParam(name="to" , required=false) String to,
			@RequestParam(name="from" , required=false) String from,
			@RequestParam(name="availableSeats" , required=false) String availableSeats,
			@RequestParam(value="maxPrice" , required=false, defaultValue = "0.0") double maxPrice,
			@RequestParam(value="minPrice" , required=false, defaultValue = "0.0") double minPrice
=======
			@RequestParam(required=false) String to,
			@RequestParam(required=false) String from,
			@RequestParam(required=false) String availableSeats,
			@RequestParam(name="maxPrice" , required=false , defaultValue="-1") int maxPrice,
			@RequestParam(name="minPrice" , required=false , defaultValue="-1") int minPrice
>>>>>>> 945bb2595024d976d3d86f63a6a0f9de99fd0435
	){
		
		return new ResponseEntity<List<Flight>>( repository.searchFlights(to, from, availableSeats, maxPrice, minPrice), HttpStatus.OK);
	}
	
<<<<<<< HEAD
		
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity createFlight(@RequestBody Flight newFlight){
		repository.save(newFlight);
		
				
		return new ResponseEntity(HttpStatus.OK);
	}
=======
	
>>>>>>> 945bb2595024d976d3d86f63a6a0f9de99fd0435
	
	
}
