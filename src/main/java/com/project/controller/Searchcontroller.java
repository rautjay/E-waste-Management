package com.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.project.dao.RequestRepository;
import com.project.dao.UserRepository;
import com.project.entities.Request;

@RestController
public class Searchcontroller {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RequestRepository requestRepository;
	
	
//search handler
	@GetMapping("/search/{query}")
	public ResponseEntity<?> search(@PathVariable("query") String query){ 
		
		List<Request> request = requestRepository.loadAllByCity(query);
		return ResponseEntity.ok(request);
	}

}
