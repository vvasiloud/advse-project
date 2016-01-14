package gr.etherasTickets.flight;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonView;

import gr.etherasTickets.View;


@Document(collection = "flights")
public class Flight {
	@JsonView(View.Summary.class)
	@Id
	private String id;
	
	@JsonView(View.Summary.class)
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private Date date;
	
	
	@JsonView(View.Summary.class)
	private String to;
	
	@JsonView(View.Summary.class)
	private String from;
	
	@JsonView(View.Summary.class)
	private int price;
	
	@JsonView(View.Summary.class)
	private int availableSeats;
	
	private int maxSeats;
	private List<Seat> seats;
	
	public Flight(){}
	
	public Flight(String to , String from , int price, int maxSeats , Date date ) {
		this.date = date;
		this.to = to;
		this.from = from;
		this.price = price;
		this.maxSeats = maxSeats;
		availableSeats = maxSeats;
		seats = createSeats(maxSeats, 6);
	}
	
	public String getId(){
		
		return id;
		
	}

	public static List<Seat> createSeats(int maxSeats , int seatRowWidth){
		List<Seat> seats = new ArrayList<Seat>();
		char seatLetter = 'A';
		int seatnumder = 1;
		for(int i = 0 ; i < maxSeats ; i++){
			seats.add(new Seat(String.format("%c%d", seatLetter , seatnumder)));
			
			seatnumder++;
			if(seatnumder > seatRowWidth){
				seatnumder = 1;
				seatLetter++;
			}	
		}
		return seats;
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

	public void setSeats(List<Seat> seats) {
		this.seats = seats;
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

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getAvailableSeats() {
		return availableSeats;
	}

	public void setAvailableSeats(int availableSeats) {
		this.availableSeats = availableSeats;
	}

	public int getMaxSeats() {
		return maxSeats;
	}

	public void setMaxSeats(int maxSeats) {
		this.maxSeats = maxSeats;
	}

	
	
	
	
	
}
