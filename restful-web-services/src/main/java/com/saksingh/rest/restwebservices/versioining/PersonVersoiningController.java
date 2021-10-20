package com.saksingh.rest.restwebservices.versioining;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonVersoiningController {
	
	//Versioning using Using URI
	@GetMapping("v1/person")
	public PersonV1 personV1() {
		return new PersonV1("Bob Charlie");
	}
	@GetMapping("v2/person")
	public PersonV2 personV2() {
		return new PersonV2(new Name("Bob", "Charlie"));
	}
	//Versioning using request params like /person/param?version=1
	@GetMapping(value="/person/param", params="version=1")
	public PersonV1 param1() {
		return new PersonV1("Bob Charlie");
	}
	
	@GetMapping(value="/person/param", params="version=2")
	public PersonV2 param2() {
		return new PersonV2(new Name("Bob", "Charlie"));
	}
	
	//Versioning using header
	@GetMapping(value="/person/header", headers ="X-API-VERSION=1")
	public PersonV2 header1() {
		return new PersonV2(new Name("Bob", "Charlie"));
	}
	
	@GetMapping(value="/person/header", headers ="X-API-VERSION=2")
	public PersonV1 header2() {
		return new PersonV1("Bob Charlie");
	}
	
	//Versioning using produce or MIME type versioning or accept header versioning
	@GetMapping(value="/person/produces", produces = "application/sak.company.app-v1-json")
	public PersonV2 produces1() {
		return new PersonV2(new Name("Bob", "Charlie"));
	}
	
	@GetMapping(value="/person/produces", produces = "application/sak.company.app-v2-json")
	public PersonV1 produces2() {
		return new PersonV1("Bob Charlie");
	}
	

}
