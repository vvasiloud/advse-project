package gr.etherasTickets.flight;
import java.util.List;




import org.springframework.data.mongodb.repository.MongoRepository;

public interface FlightRepository extends MongoRepository<Flight, String> , CustomFlightRepository  {
	
	List<Flight> searchFlights(String to, String from, String availableSeats, double maxPrice, double minPrice);
}
