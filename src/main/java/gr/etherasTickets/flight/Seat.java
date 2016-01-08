package gr.etherasTickets.flight;

import org.springframework.data.annotation.Id;

public class Seat {
	
	@Id
	private String id;
	
	private String code;
	private boolean isReserved;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public boolean isReserved() {
		return isReserved;
	}
	public void setReserved(boolean isReserved) {
		this.isReserved = isReserved;
	}
	

}
