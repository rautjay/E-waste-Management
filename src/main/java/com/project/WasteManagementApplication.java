package com.project;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.project.entities.Role;
import com.project.entities.User;
import com.project.entities.User_Role;
import com.project.services.Userservice;

@SpringBootApplication
public class WasteManagementApplication{

	
	
	
	/*
	 * @Autowired private Userservice userservice;
	 * 
	 * @Autowired private BCryptPasswordEncoder passwordEncoder;
	 */
	  
	  
	  public static void main(String[] args) {
	  SpringApplication.run(WasteManagementApplication.class, args);
	  System.out.println("starting project"); }
	  
	  // Adding ADMIN to dadabase....
	  
	  
		/*
		 * @Override public void run(String... args) throws Exception {
		 * 
		 * User user = new User(); user.setEmail("Admin01@gmail.com");
		 * user.setUserpassword(passwordEncoder.encode("Adminmh@123"));
		 * user.setState("Maharashtra"); user.setUserFirstname("Twinkle");
		 * user.setUserLastname("Deshmukh"); user.setUsergender("female");
		 * user.setUsermobile("4515478596");
		 * 
		 * 
		 * 
		 * 
		 * 
		 * Role role1 = new Role(); role1.setRoleId(45L); role1.setName("ADMIN");
		 * 
		 * Set<User_Role> userrolesSet = new HashSet<>(); User_Role userrole = new
		 * User_Role(); userrole.setRole(role1); userrole.setUser(user);
		 * userrolesSet.add(userrole);
		 * 
		 * User Admin1 = this.userservice.createUser(user, userrolesSet);
		 * 
		 * }
		 */
	 
	}


