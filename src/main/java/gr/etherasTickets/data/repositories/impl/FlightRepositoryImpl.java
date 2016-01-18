package gr.etherasTickets.data.repositories.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.util.Assert;

import gr.etherasTickets.data.repositories.CustomFlightRepository;
import gr.etherasTickets.exceptions.BadArguments;
import gr.etherasTickets.exceptions.NotFound;
import gr.etherasTickets.logic.models.Flight;


public class FlightRepositoryImpl implements CustomFlightRepository  {
	
	private final MongoOperations operations;
	
	@Autowired
	public FlightRepositoryImpl(MongoOperations operations) {
		Assert.notNull(operations, "MongoOperations must not be null!");
		this.operations = operations;
	}
	
	@Override
	public List<Flight> searchFlights(String to, String from,int availableSeats , int minPrice , int maxPrice) throws BadArguments , NotFound {
		Query query = new Query();
		
		if(to != null)
			query.addCriteria(Criteria.where("to").is(to));
		
		if(from != null)
			query.addCriteria(Criteria.where("from").is(from));
		
		if(availableSeats>0)
			query.addCriteria(Criteria.where("availableSeats").gte(availableSeats));
		
		if(maxPrice > 0 && minPrice > 0)
			if(minPrice > maxPrice)
				throw new BadArguments("minPrice must be lower than maxPrice");
			else if(minPrice == maxPrice)
				query.addCriteria(Criteria.where("price").is(maxPrice));
			else
				query.addCriteria(Criteria.where("price").gte(minPrice).and("price").lte(maxPrice));
		else if(maxPrice > 0)
			query.addCriteria(Criteria.where("price").lte(maxPrice));
		else if(minPrice > 0)
			query.addCriteria(Criteria.where("price").gte(minPrice));
		
		
		List<Flight> flightList = operations.find(query, Flight.class);
		if(flightList.isEmpty())
			throw new NotFound("Not Flights found!");
		return flightList;
	}
	
	public Flight getFlightById (String flightId)throws BadArguments , NotFound{
		if (flightId==null)
			throw new BadArguments("FlightId is null");
		
		Query query = new Query().
				addCriteria(Criteria.where("_id").is(flightId));
		
		Flight flight =  operations.findOne(query, Flight.class);
		
		if(flight == null)
			throw new NotFound("Flight with id " + flightId + " does not exist!");
		
		return flight;
	}

	@Override
	public List<String> getAllFlightsTo() throws NotFound {
		List<Flight> flights =  operations.findAll(Flight.class);
		if(flights.isEmpty())
			throw new NotFound("Flights are empty!");
		
		List<String> toList = new ArrayList<String>();
		for(Flight flight : flights){
			String to = flight.getTo();
			boolean foundSame = false;
			for(String s : toList){
				if(s.equals(to)){
					foundSame = true;
					break;
				}
			}
			if(!foundSame)
				toList.add(to);
		}
		return toList;
	}

	@Override
	public List<String> getAllFlightsFrom() throws NotFound {
		List<Flight> flights =  operations.findAll(Flight.class);
		if(flights.isEmpty())
			throw new NotFound("Flights are empty!");
		
		List<String> fromList = new ArrayList<String>();
		for(Flight flight : flights){
			String from = flight.getFrom();
			boolean foundSame = false;
			for(String s : fromList){
				if(s.equals(from)){
					foundSame = true;
					break;
				}
			}
			if(!foundSame)
				fromList.add(from);
		}
		return fromList;
	}

	
	

}
