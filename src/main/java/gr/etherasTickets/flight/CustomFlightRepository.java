package gr.etherasTickets.flight;

import java.util.List;

public interface CustomFlightRepository {
	public List<Flight> searchFlights(String to , String from,String availableSeats, String maxPrice, String minPrice);
}
