package com.saksingh.rest.restwebservices.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.saksingh.rest.restwebservices.bean.Post;
import com.saksingh.rest.restwebservices.bean.User;
import com.saksingh.rest.restwebservices.dao.PostJPARepository;
import com.saksingh.rest.restwebservices.dao.UserJPARepository;
import com.saksingh.rest.restwebservices.exception.NoPostFoundException;
import com.saksingh.rest.restwebservices.exception.UserNotFoundException;

@RestController
public class JPAUserResourceController {

	@Autowired
	UserJPARepository useJPAResouce;
	
	@Autowired
	PostJPARepository postJPAResouce;

	@GetMapping(path = "jpa/user")
	public List<User> getUsersList() {
		return useJPAResouce.findAll();
	}

	@GetMapping(path = "jpa/user/{id}")
	public Resource<User> getUsersList(@PathVariable Integer id) {
		Optional<User> user = useJPAResouce.findById(id);
		if (!user.isPresent())
			throw new UserNotFoundException("User NOt Avialble");
		Resource<User> resource = new Resource<User>(user.get());
		ControllerLinkBuilder linkTo = ControllerLinkBuilder
				.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).getUsersList());
		resource.add(linkTo.withRel("Link To All User"));

		return resource;
	}

	@PostMapping(path = "/jpa/user")
	public ResponseEntity<User> addNewUser(@RequestBody @Valid User user) {
		User savedUser = useJPAResouce.save(user);
		URI savedresouceLocation = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedUser.getId()).toUri();
		return ResponseEntity.created(savedresouceLocation).build();
	}
	
	@RequestMapping(path = "/jpa/user/{id}", method = RequestMethod.DELETE)
	public void deleteUser(@PathVariable Integer id) {
		try{useJPAResouce.deleteById(id);
			
		}catch (Exception e) {
			throw new UserNotFoundException("User Not available");		}
	}
	
	@GetMapping(path = "jpa/user/{id}/post")
	public List<Post>  getAllPostOfUser(@PathVariable Integer id) {
		Optional<User> user = useJPAResouce.findById(id);
		if (!user.isPresent())
			throw new UserNotFoundException("User NOt Avialble");
		if (user.get().getList().isEmpty()) throw new NoPostFoundException("No Post Available for this user");
		return user.get().getList();
	}
	
	@PostMapping(path = "/jpa/user/{id}/post")
	public ResponseEntity<User> createPost(@PathVariable int id, @RequestBody Post post) {
		Optional<User> savedUser = useJPAResouce.findById(id);
		if (!savedUser.isPresent()) {
			throw new UserNotFoundException("User NOt Avialble");
		}
		User user = savedUser.get();
		post.setUser(user);
		postJPAResouce.save(post);
		
		
		URI savedresouceLocation = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(post.getId()).toUri();
		return ResponseEntity.created(savedresouceLocation).build();
	}
	
	
	
}
