package gr.etherasTickets.flight;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.util.Assert;

public class FlightRepositoryImpl implements CustomFlightRepository  {
	
	private final MongoOperations operations;
	
	@Autowired
	public FlightRepositoryImpl(MongoOperations operations) {
		Assert.notNull(operations, "MongoOperations must not be null!");
		this.operations = operations;
	}
	
	@Override
<<<<<<< HEAD
	public List<Flight> searchFlights(String to, String from,String availableSeats, double maxPrice, double minPrice) {
=======
	public List<Flight> searchFlights(String to, String from,String availableSeats, int maxPrice, int minPrice) {
>>>>>>> 945bb2595024d976d3d86f63a6a0f9de99fd0435
		Query query = new Query();
		
		if(to != null)
			query.addCriteria(Criteria.where("to").is(to));
		
		if(from != null)
			query.addCriteria(Criteria.where("from").is(from));
		
		if(availableSeats!=null)
			query.addCriteria(Criteria.where("seat").is(availableSeats));
		
<<<<<<< HEAD
<<<<<<< HEAD
		if(maxPrice!=0)
=======
		if(maxPrice!=null)
>>>>>>> e5565d70fcd4c03a08672deb65bff7109f677d78
			query.addCriteria(Criteria.where("price").lte(maxPrice));
		
		if(minPrice!=0)
=======
		if(maxPrice > 0)
			query.addCriteria(Criteria.where("price").lte(maxPrice));
		
		if(minPrice > 0)
>>>>>>> 945bb2595024d976d3d86f63a6a0f9de99fd0435
			query.addCriteria(Criteria.where("price").gte(minPrice));

		return operations.find(query, Flight.class);
	}


}
