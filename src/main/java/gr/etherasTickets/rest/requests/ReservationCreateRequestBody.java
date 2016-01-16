package gr.etherasTickets.rest.requests;

import gr.etherasTickets.exceptions.*;
import gr.etherasTickets.user.Reservation;

public class ReservationCreateRequestBody {
	private String flightID;
	private int numOfSeats = -1;
	
	public void verify() throws RestException{
		if(flightID == null)
			throw new BadArguments("FlightID must not be empty!");
		if(numOfSeats >= 0)
			throw new BadArguments("Numder of seat must not greater than zero or not empty!");
	}
	
	public String getFlightID() {
		return flightID;
	}
	public void setFlightID(String flightID) {
		this.flightID = flightID;
	}
	public int getNumOfSeats() {
		return numOfSeats;
	}
	public void setNumOfSeats(int numOfSeats) {
		this.numOfSeats = numOfSeats;
	}
	
}
