package com.saksingh.rest.restwebservices.bean;

import java.util.Date;
import java.util.List;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.context.annotation.Description;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.responses.ApiResponse;

@Description("All details about User")
//@JsonFilter("User")
@Entity
public class User {

	@Id
	@GeneratedValue
	private Integer id;

	@Size(min = 2, message = "User Name should be atleast 2 character")
	@NotBlank(message = "Names Should not be Blank")
	@Pattern(regexp = "^[A-Za-z]+$", message = "Name should contain only Character")
	private String name;

	// Ignore this field in response
	// @JsonIgnore//
	// @Past(message = "Date of birth must be a past date.")
	private Date dob;

	@OneToMany(mappedBy = "user")
	private List<Post> posts;

	public List<Post> getList() {
		return posts;
	}

	public void setList(List<Post> list) {
		this.posts = list;
	}

	public User() {
	}

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
