package gr.etherasTickets.flight;

import java.util.List;

import gr.etherasTickets.exceptions.BadArguments;

public interface CustomFlightRepository {

	
	public List<Flight> searchFlights(String to , String from,int availableSeats, int minPrice , int maxPrice) throws BadArguments ;
	public Flight getFlightById(String flightId) throws BadArguments;
	public List<Seat> getSeatsById(String flightId) throws BadArguments;
}
