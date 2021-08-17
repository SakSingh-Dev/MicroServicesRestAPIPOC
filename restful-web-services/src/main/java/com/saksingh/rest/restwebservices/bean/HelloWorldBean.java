package com.saksingh.rest.restwebservices.bean;

public class HelloWorldBean  {

	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return String.format("HelloWorldBean [message=%s]", message);
	}

	public HelloWorldBean(String messa) {
		this.message = messa;
	}
}
