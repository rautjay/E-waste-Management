package com.project.controller;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.dao.RequestRepository;
import com.project.dao.UserRepository;
import com.project.entities.User;
import com.project.entities.Request;
import com.project.entities.userAddress;
import com.project.helper.message;

import net.bytebuddy.asm.Advice.OffsetMapping.Sort;

@Controller
@RequestMapping("/user")
public class Usercontroller {
    @Autowired
	private RequestRepository requestRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@ModelAttribute
	public void Addcommondata(Model model, Principal principal) {
		String username = principal.getName();
		System.out.println("username:" + username);
		User user = this.userRepository.getUserbyEmail(username);
		
		
		System.out.println("User:" + user);
		model.addAttribute("user", user);

	}
	

	
	

	/* dashboard home */
	@GetMapping("/index")
	public String user_dashboard(Model model, Principal principal) {

		return "user/user_dashboard";
	}

	/* sending request from user */
	@GetMapping("/sendrequest")
	public String open_sendrequest(Model model) {
		/* String username = principal.getName(); */

		model.addAttribute("request", new Request());
		return "user/user_sendrequest";
	}

	// processing user Request
	@PostMapping("/process_request")
	public String process_userrequest(@Valid @ModelAttribute Request request, BindingResult result, Principal principal,
			Model model,HttpSession session) {
		
		
		try {
			if (result.hasErrors()) {
                    
				
				
			}
			
			String name = principal.getName();
			User user = this.userRepository.getUserbyEmail(name);
			request.setDate(new Date());
			request.setUser(user);
		   	user.getRequests().add(request);
			this.userRepository.save(user);
			
			System.out.println("added to database");
			System.out.println("userRequest:" + request);
			
			session.setAttribute("message", new message("Your request proceded successfully!! Please wait for vendor response","warning"));
		} 
		
		catch (Exception e) {

			e.printStackTrace();
			session.setAttribute("message", new message("Somethng went wrong check properly!!","danger"));
		}

		return "user/user_sendrequest";
		
	}
	
	//per page =5[n]
	//current page= 1[page]
	@GetMapping("/collection_history/{page}")
	public String open_collectionHistory(@PathVariable("page") Integer page ,Model model,Principal principal) {
     String name = principal.getName();
     User user = this.userRepository.getUserbyEmail(name);
     
     Pageable pageable = PageRequest.of(page, 5);
     
    Page<Request> requests =   this.requestRepository.findRequestByUser(user.getUserid(),pageable);
	model.addAttribute("requests", requests);
	model.addAttribute("currentpage", page);
	model.addAttribute("totalpages", requests.getTotalPages());
	
    return "user/collection_history";
    
	}
	
	//showing Particular Status Details
	@GetMapping("/view_status/{page}")
	public String open_Viewstatus(@PathVariable("page") Integer page,Model model,Principal principal) {
	     model.addAttribute("title","showing status");
	     String name = principal.getName();
	     User user = this.userRepository.getUserbyEmail(name);
	     Pageable pageable = PageRequest.of(page,5);
	     
	    Page<Request> requests =   this.requestRepository.findRequestByUser(user.getUserid(),pageable);
		model.addAttribute("requests", requests);
		model.addAttribute("currentpage", page);
		model.addAttribute("totalpages", requests.getTotalPages());
		
		return "user/view_status";
	}
	//onclick on View Status link
	@GetMapping("/{requestId}/request_details")
	public String show_requestdetails(@PathVariable("requestId") Long requestId,Model model,Principal principal) {
	      
	
	System.out.println("requestId:"+requestId);
	Optional<Request> requestOptional = requestRepository.findById(requestId);
	Request request = requestOptional.get();
      String name =  principal.getName();
      User user = this.userRepository.getUserbyEmail(name);
      if(user.getUserid()== request.getUser().getUserid())
	model.addAttribute("request", request);
		
	return "user/request_details";
	}
	
	
	//view profile  page
	@GetMapping("/user_viewprofile")
	public String show_viewprofile(Model model) {
		
		model.addAttribute("profile","profile page");
		return "user/user_viewprofile";
	}
	
	//update profile after clicking on button
	
	@PostMapping("/update_profile")
	public String Update_profileform(Model model) {
		
		
		return "user/update_profile";
		
	}
	
	//processing update profile form
	@PostMapping("/process_updateprofile")
	public String process_updateform(@Valid @ModelAttribute("user") User user,BindingResult result,Model model,HttpSession session,Principal principal) {
		if (result.hasErrors()) {
			model.addAttribute("user", user);
			session.setAttribute("message",new message("Please enter the mandatory field", "alert alert-danger"));
			return "user/update_profile";
		}
			 else { 
                    this.userRepository.save(user);      	                               
 					
					
					session.setAttribute("message",new message("Profile Updated Successfully,","alert alert-success"));
				 
					   return "user/update_profile";	
			}
		
  
	}
	
  
	/* opening settings Tab */
	
	@GetMapping("/settings")
	public String open_settings() {
		
		return "user/settings";
	}
	
	/* change password Handler */
	
	@PostMapping("/change_password")
	public String change_password(@RequestParam("oldpassword") String oldpassword,@RequestParam("newpassword") String newpassword,Principal principal,HttpSession session) {
		
		
		
		System.out.println("oldpassword:"+ oldpassword);
		System.out.println("newpassword:"+ newpassword);
		String name = principal.getName();
		User user = userRepository.getUserbyEmail(name);
		if(this.bCryptPasswordEncoder.matches(oldpassword, user.getUserpassword())) {
			//change password
			user.setUserpassword(this.bCryptPasswordEncoder.encode(newpassword));
			this.userRepository.save(user);
			session.setAttribute("message",new message("Your password is sucessfully changed!!","alert alert-success"));
		}
		else {
			//error
			session.setAttribute("message",new message("Something Went Wrong..please enter valid password!!", "alert alert-danger"));
			return "redirect:/user/settings";
		}
		
		
		return "redirect:/user/index";
	}
	
}
