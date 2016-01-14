package gr.etherasTickets.flight;

import java.util.List;

import gr.etherasTickets.exceptions.*;

public interface CustomFlightRepository {

	
	public List<Flight> searchFlights(String to , String from,int availableSeats, int minPrice , int maxPrice) throws BadArguments , NotFound ;
	public Flight getFlightById(String flightId) throws BadArguments , NotFound;
	public List<Seat> getSeatsById(String flightId) throws BadArguments , NotFound;
}
