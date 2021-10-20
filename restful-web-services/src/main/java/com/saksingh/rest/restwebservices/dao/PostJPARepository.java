package com.saksingh.rest.restwebservices.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.saksingh.rest.restwebservices.bean.Post;

@Repository
public interface PostJPARepository extends JpaRepository<Post,Integer>{

	

	
	
}