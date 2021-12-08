package com.project.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.project.dao.RoleRepository;
import com.project.dao.UserRepository;
import com.project.entities.User;

@Service
public class userDetailsSeviceImpl implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;
	


	// getting user by email
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		/* Fetching user from database */
	User user =	userRepository.getUserbyEmail(username);
	if(user==null) {
		throw new UsernameNotFoundException("user not found!!"); 
	
	}
	customUserDetails customUserDetails = new customUserDetails(user);
		return customUserDetails;
	}

}

//saving user

