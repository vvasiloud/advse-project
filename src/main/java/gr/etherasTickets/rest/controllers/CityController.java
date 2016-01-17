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
@RequestMapping("/cities")
public class CityController {
	
	@Autowired
	private FlightRepository repository;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<String>> getCities(	
			@RequestParam(required=true) String type
	)throws RestException{
		if(type.toUpperCase().equals("TO"))
			return new ResponseEntity<List<String>>(repository.getAllFlightsTo() , HttpStatus.OK);
		else if(type.toUpperCase().equals("FROM"))
			return new ResponseEntity<List<String>>(repository.getAllFlightsFrom() , HttpStatus.OK);
		else 
			return new ResponseEntity<List<String>>(HttpStatus.BAD_REQUEST);
	}
	
}
