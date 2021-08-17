package com.saksingh.rest.restwebservices.exception;

import java.util.Date;
//Instead of Getting the Default Eception Response we are creating our own Exception Response.
/**
 * @author saksingh
 *
 */
public class CustomizedExceptionResponse{
	private Date timesStamp;
	
	private String message;
	private String details;
	
	/**
	 * @return
	 */
	public Date getTimesStamp() {
		return timesStamp;
	}

	public String getMessage() {
		return message;
	}

	public String getDetails() {
		return details;
	}

	
	
	public CustomizedExceptionResponse(Date timesStamp, String message, String details) {
		super();
		this.timesStamp = timesStamp;
		this.message = message;
		this.details = details;
	}

}
