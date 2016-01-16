package gr.etherasTickets.user;

import gr.etherasTickets.flight.Flight;
import gr.etherasTickets.flight.Seat;

import gr.etherasTickets.exceptions.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Reservation {

    private Flight flight;
    private List<Seat> seats = new ArrayList<>();

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date date;
    private String id;
    private int numberOfSeats;
    private boolean cancel;

    public Reservation(Flight flight, List<Seat> seats, Date date) {
        this.flight = flight;
        this.seats = seats;
        this.date = date;
        this.cancel=false;
        
    }
    
    public Reservation(String id,Flight flight, int numberOfSeats, Date date) {
        this.flight = flight;
        this.date = date;
        this.id=id;
        this.numberOfSeats=numberOfSeats;
        this.cancel=false;
                
    }
    
    public Reservation(){
    	
    	
    }
    
    public Reservation(Flight flight, int numberOfSeats , Date date) {
        this.flight = flight;
        this.numberOfSeats = numberOfSeats;
        this.date = date;
        this.cancel=false;
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

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }
    
    public void setCancel(boolean cancel){
    	
    	this.cancel=cancel;
    }
    public boolean getCancel (){
    
    	return cancel;
    }
    

}
