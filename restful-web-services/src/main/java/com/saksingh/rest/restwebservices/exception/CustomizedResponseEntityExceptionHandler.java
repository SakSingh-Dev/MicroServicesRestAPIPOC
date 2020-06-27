package com.saksingh.rest.restwebservices.exception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.databind.deser.impl.BeanAsArrayDeserializer;


/**
 * @author saksingh
 *
 */
//This Exception Handler should be available to all Controller so we will ad below annotation
@ControllerAdvice
//Make this class as Controller too
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	public CustomizedResponseEntityExceptionHandler() {
		// TODO Auto-generated constructor stub
	}

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<CustomizedExceptionResponse> handleAllException(Exception ex, WebRequest request) throws Exception {
		
		CustomizedExceptionResponse customizedExceptionResponse = new CustomizedExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<CustomizedExceptionResponse> (customizedExceptionResponse,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<CustomizedExceptionResponse> handleUserNotFoundExceptionException(Exception ex, WebRequest request) throws Exception {
		
		CustomizedExceptionResponse customizedExceptionResponse = new CustomizedExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<CustomizedExceptionResponse> (customizedExceptionResponse,HttpStatus.NOT_FOUND);
	}
	
	/**
	 * Customize the response for MethodArgumentNotValidException.
	 * <p>This method delegates to {@link #handleExceptionInternal}.
	 * @param ex the exception
	 * @param headers the headers to be written to the response
	 * @param status the selected response status
	 * @param request the current request
	 * @return a {@code ResponseEntity} instance
	 */
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		CustomizedExceptionResponse customizedExceptionResponse = new CustomizedExceptionResponse(new Date(),"Validation Failed",
				ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage());
		
		return new ResponseEntity<>(customizedExceptionResponse,HttpStatus.BAD_REQUEST);
	}

}
