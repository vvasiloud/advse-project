package gr.etherasTickets.user;

import gr.etherasTickets.exceptions.*;
import gr.etherasTickets.flight.*;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import gr.etherasTickets.rest.requests.ReservationCreateRequestBody;

@RestController
@RequestMapping("/users/{userid}/reservations")
public class ReservationController {

	@Autowired
	private FlightRepository flightRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<String> addReservation(@RequestParam String userid , @RequestBody ReservationCreateRequestBody reservationBody) throws RestException{
		User user = userRepository.getUserById(userid);
		if(user == null)
			throw new BadArguments("User with id "+userid+" does not exist");
		
		reservationBody.verify();
		
		Flight flight = flightRepository.getFlightById(reservationBody.getFlightID());
		if(flight == null)
			throw new BadArguments("Flight with id "+reservationBody.getFlightID()+" does not exist");
		
		if(user.getBalance() < flight.getPrice())
			throw new LogicError("User "+user.getUsername()+" does not have enough money!");
		
		if(flight.getAvailableSeats() < reservationBody.getNumOfSeats())
			throw new LogicError("Flight with id "+reservationBody.getFlightID()+" does not have availables seats!");
				
		flight.setAvailableSeats(flight.getAvailableSeats() - reservationBody.getNumOfSeats());
		user.setBalance(user.getBalance() - flight.getPrice());
			
		user.addReservation(new Reservation(flight, new ArrayList<Seat>(), new Date()));	
		
		userRepository.save(user);
		
		String reservationId = "getreservationId" ;
		
		return new ResponseEntity<String>(String.format("{'link':'/users/%s/reservations/%s'}",userid , reservationId) , HttpStatus.OK);
	}
	
	//Todo create Seat list my flight
}
