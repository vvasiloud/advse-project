package gr.etherasTickets.flight;


import org.springframework.data.mongodb.repository.MongoRepository;

public interface FlightRepository extends MongoRepository<Flight, String> , CustomFlightRepository  {
	
<<<<<<< HEAD
	List<Flight> searchFlights(String to, String from, String availableSeats, double maxPrice, double minPrice);
=======
>>>>>>> 945bb2595024d976d3d86f63a6a0f9de99fd0435
}
