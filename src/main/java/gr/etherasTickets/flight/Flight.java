package gr.etherasTickets.flight;

import java.sql.Date;
import java.util.ArrayList;

import org.springframework.data.annotation.Id;

public class Flight {
	@Id
	private String id;
	
	private Date date;
	private ArrayList<Seat>  seats;
	private double price;
	private int availableSeats;
	
	private String to;
	private String from;
	
	
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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
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
}
