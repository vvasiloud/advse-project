package Flight;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;


public interface FlightRepository extends MongoRepository<Flight, String> {
	List<Flight> findByFrom(String from);
}
