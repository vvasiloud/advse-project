package gr.etherasTickets.rest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.annotation.JsonView;

import gr.etherasTickets.View;
import gr.etherasTickets.exceptions.BadArguments;
import gr.etherasTickets.exceptions.NotFound;
import gr.etherasTickets.exceptions.RestException;
import gr.etherasTickets.flight.FlightRepository;
import gr.etherasTickets.logic.models.Flight;
import gr.etherasTickets.logic.models.Seat;


@RestController
@RequestMapping("/flights")
public class FlightController {
	
	@Autowired
	private FlightRepository repository;
	
	@JsonView(View.Summary.class)
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Flight>> getFlights(	
			@RequestParam(required=false) String to,
			@RequestParam(required=false) String from,
			@RequestParam(required=false , defaultValue="-1") int availableSeats,
			@RequestParam(name="minPrice" , required=false , defaultValue="-1") int minPrice,
			@RequestParam(name="maxPrice" , required=false , defaultValue="-1") int maxPrice
	)throws RestException{
		return new ResponseEntity<List<Flight>>( repository.searchFlights(to, from, availableSeats, minPrice, maxPrice), HttpStatus.OK);
	}
	
	@RequestMapping( value = "/{flightId}", method = RequestMethod.GET )
	public ResponseEntity<Flight> getFlightsById(@PathVariable String flightId) throws RestException{
		return new ResponseEntity<> (repository.getFlightById(flightId),HttpStatus.OK);
	}
	
	
	
	/*
	@RequestMapping( value = "/{flightId}/seats", method = RequestMethod.GET )
	public ResponseEntity<List<Seat>> getSeatsByFlightId(@PathVariable String flightId) throws RestException{
		return new ResponseEntity<> (repository.getSeatsById(flightId),HttpStatus.OK);
	}
	*/
}
