package com.saksingh.rest.restwebservices.bean;

import java.util.Date;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
@JsonFilter("User")
public class User {
	private Integer id;
	@Size(min = 2, message = "User Name should be atleast 2 character")
	@NotBlank(message = "Names Should not be Blank")
	@Pattern(regexp = "^[A-Za-z]+$", message = "Name should contain only Character")
	private String name;
	
	//Ignore this field in response
	//@JsonIgnore//
    @Past(message = "Date of birth must be a past date.")
	private Date dob;

	/*
	 * protected User() { }
	 */

	public User(int i, String string, Date date) {
		this.id = i;
		this.name = string;
		this.dob = date;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}
    
}
