package com.saksingh.rest.restwebservices.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class NoPostFoundException extends RuntimeException {
	
	private static final long serialVersionUID = -5432068150819987030L;

	public NoPostFoundException(String message) {
		super(message);
	}

}
