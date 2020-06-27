package com.saksingh.rest.restwebservices.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// Added Response status as 404-User is not avilable Instead of
// 500-Internal server Error(BY default spring takes 500)
/**
 * @author saksingh
 *
 */
@ResponseStatus(code = HttpStatus.NOT_FOUND) 
public class UserNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5432068150817687030L;

	public UserNotFoundException(String message) {
		super(message);
	}

}
