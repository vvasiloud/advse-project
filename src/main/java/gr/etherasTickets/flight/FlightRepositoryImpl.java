package gr.etherasTickets.flight;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.util.Assert;

import gr.etherasTickets.exceptions.BadArguments;

public class FlightRepositoryImpl implements CustomFlightRepository  {
	
	private final MongoOperations operations;
	
	@Autowired
	public FlightRepositoryImpl(MongoOperations operations) {
		Assert.notNull(operations, "MongoOperations must not be null!");
		this.operations = operations;
	}
	
	@Override
	public List<Flight> searchFlights(String to, String from,int availableSeats , int minPrice , int maxPrice) throws BadArguments {
		Query query = new Query();
		
		if(to != null)
			query.addCriteria(Criteria.where("to").is(to));
		
		if(from != null)
			query.addCriteria(Criteria.where("from").is(from));
		
		if(availableSeats>0)
			query.addCriteria(Criteria.where("seat").is(availableSeats));
		
		if(maxPrice > 0 && minPrice > 0)
			if(minPrice > maxPrice)
				throw new BadArguments("minPrice must be lower than maxPrice");
			else if(minPrice == maxPrice)
				query.addCriteria(Criteria.where("price").is(maxPrice));
		else if(maxPrice > 0)
			query.addCriteria(Criteria.where("price").lte(maxPrice));
		else if(minPrice > 0)
			query.addCriteria(Criteria.where("price").gte(minPrice));

		return operations.find(query, Flight.class);
	}
	
	public List<Seat> getSeatsById(String flightId)throws BadArguments{

		return getFlightById(flightId).getSeats();
	}
	
	public Flight getFlightById (String flightId)throws BadArguments{
		
		Query query = new Query();
		if (flightId!=null)
			query.addCriteria(Criteria.where("_id").is(flightId));
		
						
		return operations.findOne(query, Flight.class);
		
	}

	
	

}
