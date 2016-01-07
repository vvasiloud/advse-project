package gr.etherasTickets.flight;

import org.springframework.data.annotation.Id;

public class Flight {
	@Id
	private String id;
	
	
	
	private String to;
	private String from;
	
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
