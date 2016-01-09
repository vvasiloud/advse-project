package gr.etherasTickets.flight;

<<<<<<< HEAD
import java.util.List;
import java.sql.Date;
=======
import java.util.Date;
>>>>>>> 945bb2595024d976d3d86f63a6a0f9de99fd0435
import java.util.ArrayList;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;


@Document(collection = "flights")
public class Flight {
	@Id
	private String id;
	
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private Date date;
<<<<<<< HEAD
	private List<Seat>  seats;
	private double price;
	private int availableSeats;
=======
>>>>>>> 945bb2595024d976d3d86f63a6a0f9de99fd0435
	
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

	public List<Seat> getSeats() {
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
