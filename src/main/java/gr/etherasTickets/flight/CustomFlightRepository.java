package gr.etherasTickets.flight;

import java.util.List;

import gr.etherasTickets.exceptions.BadArguments;

public interface CustomFlightRepository {

	
	public List<Flight> searchFlights(String to , String from,int availableSeats, int maxPrice, int minPrice) throws BadArguments ;

}
