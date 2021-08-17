package com.saksingh.rest.restwebservices.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.saksingh.rest.restwebservices.dao.UserDao;

@RestController
@RequestMapping("/query")
public class QueryOrderController {

	@Autowired
	UserDao userdao;

	@GetMapping(path = "/name/{id}/{name}")
	public String getName(@PathVariable String id, @PathVariable String name) {
		return "ket" + id + name;
	}

	//url will be like--http://localhost:8080/query/employee/id/4?issueFrom=14-MAY-2020&issueTo=14-June-2020
	@GetMapping(path = "/employee/id/{id}")
	public ResponseEntity<String> getInvoiceByDate(@PathVariable int id,
			@RequestParam(value = "issueFrom", required = false) Date fromDate,
			@RequestParam(value = "issueTo", required = false) Date toDate) {
		return ResponseEntity.ok("Request URL:" + id + "::" + "Issue From Date: "
				+ (fromDate != null ? fromDate.toString() : "fromDate is null") + " Issue to Date: "
				+ (toDate != null ? toDate.toString() : "fromDate is null"));

	}
}
