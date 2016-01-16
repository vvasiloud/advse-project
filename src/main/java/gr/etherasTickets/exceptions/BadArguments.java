package gr.etherasTickets.exceptions;

import org.springframework.http.HttpStatus;

public class BadArguments extends RestException {
	private static final long serialVersionUID = 1L;
	
	public BadArguments(String message) {
		super(message , HttpStatus.BAD_REQUEST);
	}
}
