package com.project.controller;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.project.dao.UserRepository;
import com.project.entities.Role;
import com.project.entities.User;
import com.project.entities.User_Role;
import com.project.helper.message;
import com.project.services.Userservice;

@Controller

public class Homecontroller {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private Userservice userservice;

	/* Home page */ 
	@GetMapping("/")
	public String Home(Model model) {
		model.addAttribute("title", "Home-E Wastemanagement");

		return "home";

	}

	/* Register navigation page */
	@GetMapping("/Register")
	public String usersignup(Model model) {
		model.addAttribute("title", "User Registration");
		model.addAttribute("user", new User());
		return "Register";
	}

	/* user registration form submission */
	@PostMapping("/user_register")
	public String signup_process(@Valid @ModelAttribute("user") User user, BindingResult result, Model model, HttpSession session) throws Exception {
       
	//for adding Role
		
		Set<User_Role> roleset = new HashSet<>();
	     Role role = new Role();
	     role.setRoleId(46L);
	     role.setName("USER");
	     
	     User_Role userrole =  new User_Role();
	     userrole.setUser(user);
	     userrole.setRole(role);
	     roleset.add(userrole);
	     
	     //ROLE ADDING ENDS HERE
		
		
		if (result.hasErrors()) {
			model.addAttribute("user", user);
			session.setAttribute("message",new message("Please enter the mandatory field", "alert alert-danger"));
			return "Register";
		} else {
			
		    user.setUserpassword(passwordEncoder.encode(user.getUserpassword()));
			
			
			User reg_user = this.userservice.createUser(user, roleset);
              
			System.out.println("user:" + reg_user);
			model.addAttribute("user", new User());
			
			session.setAttribute("message_sucess",new message("Registration sucessfull,","alert alert-primary"));
		 
			return "Register";

		}
	}
	
	
	//user Login
	
	
	/* Login navigation page */
	@GetMapping("/Login")
	public String showloginpage(Model model) {
		model.addAttribute("title", "user-Login");

		return "login";
		
		
		
		
		
}
	
	
	//sucess login
	
	/*
	 * @GetMapping("/success") public String loginPage(Model model, Authentication
	 * authentication) { if(authentication.getAuthorities().contains(new
	 * SimpleGrantedAuthority("ADMIN"))) { return "admin/Admin_dashboard"; } else
	 * if(authentication.getAuthorities().contains(new
	 * SimpleGrantedAuthority("USER"))) { return "user/user_dashboard"; } else {
	 * return "login"; }
	 * 
	 * }
	 */
  
}


	 
		
