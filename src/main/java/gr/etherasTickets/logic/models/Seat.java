package gr.etherasTickets.logic.models;

import org.springframework.data.annotation.Id;

public class Seat {
	
	@Id
	private String id;
	
	private String code;
	private boolean isReserved;
	
	public Seat(){}
	
	public Seat(String code){
		this.code = code;
		isReserved = false;
	}
	
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
