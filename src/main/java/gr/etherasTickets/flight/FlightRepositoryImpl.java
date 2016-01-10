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
	public List<Flight> searchFlights(String to, String from,int availableSeats, int maxPrice, int minPrice) throws BadArguments {
		Query query = new Query();
		
		if(to != null)
			query.addCriteria(Criteria.where("to").is(to));
		
		if(from != null)
			query.addCriteria(Criteria.where("from").is(from));
		
		if(availableSeats>0)
			query.addCriteria(Criteria.where("seat").is(availableSeats));
		
		if((maxPrice > 0 && minPrice > 0) && minPrice > maxPrice)
			throw new BadArguments("minPrice must be lower than maxPrice");
		
		if(maxPrice > 0)
		query.addCriteria(Criteria.where("price").lte(maxPrice));
		
			
		if(minPrice > 0)
			query.addCriteria(Criteria.where("price").gte(minPrice));

		return operations.find(query, Flight.class);
	}


}
