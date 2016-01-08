package gr.etherasTickets.flight;

import java.util.Date;
import java.util.ArrayList;

import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

public class Flight {
	@Id
	private String id;
	
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private Date date;
	
	private String to;
	private String from;
	
	private int price;
	
	private ArrayList<Seat>  seats;
	private int availableSeats;
	private int maxSeats;
	
	
	public Flight(){}
	
	public Flight(String from , String to , int price, int maxSeats , Date date) {
		this.date = date;
		this.to = to;
		this.from = from;
		this.price = price;
		this.maxSeats = maxSeats;
		availableSeats = maxSeats;
	}

	
	
	
	
	public int getAvailableSeats() {
		return availableSeats;
	}

	public void setAvailableSeats(int availableSeats) {
		this.availableSeats = availableSeats;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public ArrayList<Seat> getSeats() {
		return seats;
	}

	public void setSeats(ArrayList<Seat> seats) {
		this.seats = seats;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public int getMaxSeats() {
		return maxSeats;
	}

	public void setMaxSeats(int maxSeats) {
		this.maxSeats = maxSeats;
	}
}
