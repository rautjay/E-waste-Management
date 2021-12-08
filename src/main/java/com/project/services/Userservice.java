package com.project.services;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.project.entities.User;
import com.project.entities.User_Role;

@Service
public interface Userservice {
	
	
	//Creating User
	
	public User createUser(User user,Set<User_Role> userroles) throws Exception;

}
