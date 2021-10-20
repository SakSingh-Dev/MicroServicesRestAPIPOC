package com.saksingh.rest.restwebservices.versioining;

public class Name {
	private String firstName;
	private String secondName;
	
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getSecondName() {
		return secondName;
	}
	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}
	
	public Name(String fname, String sName) {
		this.firstName = fname;
		this.secondName = sName;
	}
	
	
}
