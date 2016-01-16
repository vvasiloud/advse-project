package gr.etherasTickets.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class RestException extends Exception {
	private static final long serialVersionUID = 1L;
	private HttpStatus httpStatus;
	
	public RestException(String message  ,HttpStatus httpStatus) {
		super(message);
		this.httpStatus = httpStatus;
	}
	
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
	
	public ResponseEntity<String> getResponseEntity(){
		return new ResponseEntity<String>(getMessage(),httpStatus);
	}
}
