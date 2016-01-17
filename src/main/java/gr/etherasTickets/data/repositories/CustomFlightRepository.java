package gr.etherasTickets.data.repositories;

import java.util.List;

import gr.etherasTickets.exceptions.*;
import gr.etherasTickets.logic.models.Flight;

public interface CustomFlightRepository {
	public List<Flight> searchFlights(String to , String from,int availableSeats, int minPrice , int maxPrice) throws BadArguments , NotFound ;
	public Flight getFlightById(String flightId) throws BadArguments , NotFound;
	public List<String> getAllFlightsTo() throws NotFound;
	public List<String> getAllFlightsFrom() throws NotFound;
}
