package gr.etherasTickets.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.util.Assert;

import gr.etherasTickets.exceptions.NotFound;
import gr.etherasTickets.exceptions.RestException;

public class ReservationRepositoryImpl implements CustomReservationRepository {

	 private final MongoOperations operations; //Class for communicating with mongoDB

	    @Autowired
	    public ReservationRepositoryImpl(MongoOperations operations) {
	        Assert.notNull(operations, "MongoOperations must not be null!");
	        this.operations = operations;
	    }


}
