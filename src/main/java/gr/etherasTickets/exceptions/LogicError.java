package gr.etherasTickets.exceptions;

import org.springframework.http.HttpStatus;

public class LogicError extends RestException {

	public LogicError(String message) {
		super(message, HttpStatus.BAD_REQUEST);
	}

}
