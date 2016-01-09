package gr.etherasTickets.flight;

import java.util.List;

public interface CustomFlightRepository {
<<<<<<< HEAD
	public List<Flight> searchFlights(String to , String from,String availableSeats, double maxPrice, double minPrice);
=======
	public List<Flight> searchFlights(String to , String from,String availableSeats, int maxPrice, int minPrice);
>>>>>>> 945bb2595024d976d3d86f63a6a0f9de99fd0435
}
