package com.project.services.Impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.project.dao.RoleRepository;
import com.project.dao.UserRepository;
import com.project.entities.User;
import com.project.entities.User_Role;
import com.project.services.Userservice;

@Component
public class UserServiceImpl implements Userservice{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	// For Creating user..
	@Override
	public User createUser(User user, Set<User_Role> userroles) throws Exception {
		
		User local =	this.userRepository.getUserbyEmail(user.getEmail());
	  if(local!=null) {
		  System.out.println("USer already there!!");
		 throw new Exception("User already Present!!");
	  }else {
		  //creating user
		  
		  for(User_Role UR:userroles) {
			  roleRepository.save(UR.getRole());
		  }
		  
		  user.getUserroles().addAll(userroles);
		  local= this.userRepository.save(user);
	  }
		return null;
	}
	
	//for getting user....
	
	

}
