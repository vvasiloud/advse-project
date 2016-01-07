package gr.etherasTickets.flight;


import org.springframework.data.mongodb.repository.MongoRepository;

public interface FlightRepository extends MongoRepository<Flight, String> , CustomFlightRepository  {
	
	
}
