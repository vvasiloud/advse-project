package gr.etherasTickets.data.repositories;


import org.springframework.data.mongodb.repository.MongoRepository;

import gr.etherasTickets.logic.models.Flight;

public interface FlightRepository extends MongoRepository<Flight, String> , CustomFlightRepository  {
	
}
