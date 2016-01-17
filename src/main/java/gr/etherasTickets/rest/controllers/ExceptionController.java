package gr.etherasTickets.rest.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import gr.etherasTickets.exceptions.*;

@ControllerAdvice
public class ExceptionController {
	
	@ExceptionHandler(RestException.class)
	public ResponseEntity<String> restExceptionHandler(RestException ex){
		return ex.getResponseEntity();
	}
}

