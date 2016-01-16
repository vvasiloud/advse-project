package gr.etherasTickets.exceptions;

import org.springframework.http.HttpStatus;

public class NotFound extends RestException {
	private static final long serialVersionUID = 1L;
	
	public NotFound(String message) {
		super(message , HttpStatus.NOT_FOUND);
	}
}
