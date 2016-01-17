package gr.etherasTickets.logic.models;

import gr.etherasTickets.exceptions.*;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Reservation {

    private Flight flight;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date date;
    private String id;
    private int numOfSeats;
    private boolean cancel;
    
    public Reservation(String id,Flight flight, int numberOfSeats, Date date) {
        this.flight = flight;
        this.date = date;
        this.id=id;
        this.numOfSeats=numberOfSeats;
        this.cancel=false;
    }
    
    public Reservation(){}
    
    public Reservation(Flight flight, int numberOfSeats , Date date) {
       this(UUID.randomUUID().toString() , flight , numberOfSeats , date);
    }
    
    
    public int getCost(){
    	return flight.getPrice() * numOfSeats;
    }
   
    public String getId(){
    	
    	return id;
    }
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public void setCancel(boolean cancel){
    	
    	this.cancel=cancel;
    }
    public boolean isCancel (){
    
    	return cancel;
    }

	public int getNumOfSeats() {
		return numOfSeats;
	}

	public void setNumOfSeats(int numberOfSeats) {
		this.numOfSeats = numberOfSeats;
	}
    

}
