package com.saksingh.rest.restwebservices.dao;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.saksingh.rest.restwebservices.bean.User;

@Repository
public interface UserJPARepository extends JpaRepository<User,Integer>{

	

	
	
}