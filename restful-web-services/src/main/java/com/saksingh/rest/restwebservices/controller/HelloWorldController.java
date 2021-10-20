package com.saksingh.rest.restwebservices.controller;

import java.net.URI;
import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.saksingh.rest.restwebservices.bean.HelloWorldBean;
import com.saksingh.rest.restwebservices.bean.User;
import com.saksingh.rest.restwebservices.dao.UserDao;
import com.saksingh.rest.restwebservices.exception.UserNotFoundException;

/*Ask MVC to consider this class 
as Rest Controller to 
handle Rest request like GET,POST ,PUT-So we ause annotation- @Rest COntoller*/
/**
 * @author saksingh
 *
 */
@RestController
@RequestMapping("/HelloWorldController")
public class HelloWorldController {
	@Autowired
	UserDao userDao;
	// Autowiring the messageSource which we have created in main Application class.
	// We cant Create more than one MessageSource object as this is Singleton.
	@Autowired
	MessageSource messageSource;

	/*
	 * Create one GET method who can hadle to return a String Object with
	 * "Hello World"..Both are same @RequestMapping(method = RequestMethod.GET, path
	 * = "/hello-wolrd")
	 * 
	 * @GetMapping(path = "/hello-world")
	 */
	// @RequestMapping(method = RequestMethod.GET, path = "/hello-wolrd")
	@GetMapping(path = "/hello-world")
	public String printHelloWorld() {
		return "Hello World";
	}
//Based on  Locale we can get the value from database
	@GetMapping(path = "/hello-world-internationalization")
	public String printHelloWorld118n(@RequestHeader(name = "Accept-Language", required = false) Locale locale) {
		return messageSource.getMessage("good.morning.message", null, locale);
	}

	@GetMapping(path = "/hello-world-bean")
	public HelloWorldBean printHelloWorldBean() {
		return new HelloWorldBean("Hello World");
	}

	@GetMapping(path = "/hello-world-bean/path-var/{name}")
	public HelloWorldBean printHelloPathVar(@PathVariable String name) {
		return new HelloWorldBean("Hello World " + name);
	}

	@GetMapping(path = "/user")
	public List<User> getUsersList() {
		return userDao.getAllUsers();
	}
	
	//Filtering Response
	@GetMapping(path = "/user-filter")
	public MappingJacksonValue getFilteredUsersList() {
		List<User> users = userDao.getAllUsers();
		MappingJacksonValue mppiJacksonValue =  new MappingJacksonValue(users);
		mppiJacksonValue.setFilters(new SimpleFilterProvider().addFilter("User", SimpleBeanPropertyFilter.filterOutAllExcept("name")));
		return mppiJacksonValue;
	}

	// HATEOAS-Hypermedia as the Engine of Application State.
	// HATEOAS Means to provide the hyperlink to the user/resource. FOr example when
	// we retrieve a list then each list have a hyperlink or single resource has a
	// hyperlink
	
	/*HATEOUS UPdate in v2.5.0
	 * HATEOAS code with earlier versions looks
	 * something like this: Resource and ControllerLinkBuilder instead of EntityModel and WebMvcLinkBuilder.
	 */

	@GetMapping(path = "/user/{id}")
	public Resource<User> findUser(@PathVariable Integer id) {
		User user = userDao.findUser(id);
		if (null == user)
			throw new UserNotFoundException(String.format("User ID-[%s] is not avialable in Database", id));
		// HATEOAS- We would want to link to this method-getUsersList
		Resource<User> resource = new Resource<User>(user);
		ControllerLinkBuilder linkTo = ControllerLinkBuilder
				.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).getUsersList());
		resource.add(linkTo.withRel("Link To All User"));
		
		return resource;
	}

	/*
	 * @RequestBody- This indicates the request body architecture When we get the
	 * request to create a user then we want to validate the User-Request body
	 * using @Valid
	 * 
	 * @Valid-Comes with Javax Validation package to validate the Bean Whenever we
	 * do POst operation we need to send the Status code of Response wether it
	 * success or not and then URI of newly created resource
	 */
    @RequestMapping(method=RequestMethod.POST,path = "/user")

	//@PostMapping(path = "/user")
	public ResponseEntity<User> addNewUser(@RequestBody @Valid User user) {
		User savedUser = userDao.save(user);
		URI savedresouceLocation = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedUser.getId()).toUri();
		return ResponseEntity.created(savedresouceLocation).build();
	}

	// @DeleteMapping(path = "/user/{id}")
	@RequestMapping(path = "/user/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<User> deleteUser(@PathVariable Integer id) {
		User user = userDao.deleteById(id);
		if (user == null) {
			throw new UserNotFoundException("User Not available");
		}
		return ResponseEntity.noContent().build();// Send empty 200 status.
	}
	

}
