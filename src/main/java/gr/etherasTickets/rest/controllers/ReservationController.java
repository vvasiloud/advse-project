package gr.etherasTickets.rest.controllers;

import gr.etherasTickets.data.repositories.FlightRepository;
import gr.etherasTickets.data.repositories.UserRepository;
import gr.etherasTickets.exceptions.*;
import gr.etherasTickets.flight.*;
import gr.etherasTickets.logic.models.Flight;
import gr.etherasTickets.logic.models.Reservation;
import gr.etherasTickets.logic.models.User;
import gr.etherasTickets.rest.resources.ReservationResource;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users/{userid}/reservations")
public class ReservationController {


	@Autowired
	private FlightRepository flightRepository;

	@Autowired
	private UserRepository userRepository;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<String> addReservation(@PathVariable String userid , @RequestBody ReservationResource reservationResource) throws RestException{
		User user = userRepository.getUserById(userid);

		reservationResource.verifyForCreate();

		Flight flight = flightRepository.getFlightById(reservationResource.getFlightID());

		Reservation newReservation = new Reservation(flight , reservationResource.getNumOfSeats() , new Date());
		
		
		flight.removeSeats(reservationResource.getNumOfSeats());
		user.removeBalance(newReservation.getCost());
		
		user.addReservation(newReservation);	
		
		userRepository.save(user);
		flightRepository.save(flight);
		
		return new ResponseEntity<String>(String.format("/users/%s/reservations/%s",userid , newReservation.getId()) , HttpStatus.OK);
	}


	@RequestMapping(path = "/{reservationId}" , method = RequestMethod.DELETE)
	public ResponseEntity<String> removeReservation(@PathVariable String userid, @PathVariable String reservationId )throws RestException{

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
	public ResponseEntity<List<Reservation>> getReservations(@PathVariable String userid) throws RestException{
		User user = userRepository.getUserById(userid);
		
		if(user.getReservations().isEmpty())
			throw new NotFound("User with id "+ user.getId()+ "Doesn't have reservations");
		
		return new ResponseEntity<List<Reservation>>(user.getReservations(),HttpStatus.OK);
	}
	
	@RequestMapping(path = "/{reservationId}" , method = RequestMethod.GET)
	public ResponseEntity<Reservation> getReservation(
			@PathVariable String userid , 
			@PathVariable String reservationId  
	) throws RestException{
		User user = userRepository.getUserById(userid);
		return new ResponseEntity<Reservation>(user.getReservation(reservationId),HttpStatus.OK);
	}
	
	@RequestMapping(path = "/{reservationId}" , method = RequestMethod.PUT)
	public ResponseEntity<String> updateReservation(
			@PathVariable String userid , 
			@PathVariable String reservationId , 
			@RequestBody ReservationResource reservationResource
	) throws RestException{
		reservationResource.verifyForUpdate();
		
		User user = userRepository.getUserById(userid);
		Reservation reservation = user.getReservation(reservationId);
		Flight flight = reservation.getFlight();
		
		if(reservation.getNumOfSeats() == reservationResource.getNumOfSeats())
			throw new BadArguments("Can not update the same numder of seats");
		
		if(reservation.getNumOfSeats() > reservationResource.getNumOfSeats()){
			flight.addSeats(reservation.getNumOfSeats() - reservationResource.getNumOfSeats());
		}else if(reservation.getNumOfSeats() < reservationResource.getNumOfSeats())
			flight.removeSeats(reservationResource.getNumOfSeats() - reservation.getNumOfSeats());
		
		int oldCost = reservation.getCost();
		reservation.setNumOfSeats(reservationResource.getNumOfSeats());
		int newCost = reservation.getCost();
		
		if(oldCost > newCost)
			user.addBalance(oldCost - newCost);
		else if(newCost > oldCost)
			user.removeBalance(newCost - oldCost);
			
		userRepository.save(user);
		flightRepository.save(flight);
		
		return new ResponseEntity<String>(String.format("/users/%s/reservations/%s",userid , reservation.getId()) , HttpStatus.OK);
	}
}
