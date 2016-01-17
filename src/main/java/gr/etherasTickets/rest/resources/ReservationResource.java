package gr.etherasTickets.rest.resources;

import gr.etherasTickets.exceptions.*;
import gr.etherasTickets.logic.models.Reservation;

public class ReservationResource {
	private String flightID;
	private int numOfSeats = -1;
	
	public ReservationResource(){}
	public ReservationResource(String flightID, int numOfSeats) {
		this.flightID = flightID;
		this.numOfSeats = numOfSeats;
	}
	
	public String toJson(){
		return String.format("{\"flightID\":\"%s\" , \"numOfSeats\":%d}", flightID , numOfSeats);
	}

	public void verifyForCreate() throws RestException{
		verifyFlightID();
		verifyNumOfSeats();
	}
	
	public void verifyForUpdate() throws RestException{
		verifyNumOfSeats();
	}
	
	public void verifyFlightID() throws RestException{
		if(flightID == null)
			throw new BadArguments("FlightID must not be empty!");
	}
	
	public void verifyNumOfSeats() throws RestException{
		if(numOfSeats <= 0)
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
