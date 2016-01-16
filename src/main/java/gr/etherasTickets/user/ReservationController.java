package gr.etherasTickets.user;

import gr.etherasTickets.exceptions.*;
import gr.etherasTickets.flight.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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


	@RequestMapping(path = "/{reservationId}" , method = RequestMethod.DELETE)
	public ResponseEntity<String> removeReservation(@RequestParam String userid, @RequestParam String reservationId )throws RestException{

		User user = userRepository.getUserById(userid);
		if(user == null)
			throw new BadArguments("User with id "+userid+" does not exist");

		for (Reservation r : user.getReservations())
			if (r.getId().equals(reservationId)){
				
				r.setCancel(true);
				
				break;
			}
		
		userRepository.save(user);
		
		return new ResponseEntity<String>(HttpStatus.OK);
		
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Reservation>> getReservations(@RequestParam String userid) throws RestException{
		User user = userRepository.getUserById(userid);
		if(user == null)
			throw new BadArguments("User with id "+userid+" does not exist");
		
		if(user.getReservations().isEmpty())
			throw new NotFound("User with id "+ user.getId()+ "Doesn't have reservations");
		
		return new ResponseEntity<List<Reservation>>(user.getReservations(),HttpStatus.OK);


	//Todo create Seat list my flight
	}
}
