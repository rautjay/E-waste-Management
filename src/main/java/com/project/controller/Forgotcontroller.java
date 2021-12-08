package com.project.controller;

import java.security.Principal;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.dao.UserRepository;
import com.project.entities.User;
import com.project.services.EmailService;

@Controller
public class Forgotcontroller {
	
	@Autowired
	private EmailService emailService;
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	Random random = new Random(1000);
	/* email id form open */
	@GetMapping("/forgot")
	public String open_emailform() {
		
		return "forgot_email_form";
	}
	
	@PostMapping("/send_OTP")
	public String send_OTP(@RequestParam("email") String email,HttpSession session) {
		System.out.println("email:"+ email);
		/* genrating $digit random number */

		int OTP = random.nextInt(999999);
		System.out.println("OTP:"+OTP);
		
		//Write code for OTP to Eamil....
		String subject = "OTP from Ewaste management";
		String message = "<h1>Your OTP is ="+OTP+"</h1>";
		String to = email;
		String from ="jayraut05@gmail.com"; 
		
		boolean flag = this.emailService.sendEmail(message,subject, to,from);
		
		if(flag) {
			
			session.setAttribute("myotp", OTP);
			session.setAttribute("email", email);
			
		return "varify_otp";
		}else {
			
			session.setAttribute("message","please,check Your email again!!");
		
		return "forgot_email_form";
		}
	}
	
	///verify OTP...
	
	@PostMapping("/otp-verification")
    public String otp_verificatrion(@RequestParam("OTP") int OTP,HttpSession session ){
		
	int myotp= (Integer) session.getAttribute("myotp");
	String email = (String) session.getAttribute("email");
      		
	if(myotp==OTP){
		
		//password chnge form
		User user = this.userRepository.getUserbyEmail(email);
		
		if(user==null)
		{
			//send error message
			
			session.setAttribute("message","User not exists!!");
			
			return "forgot_email_form";
			
		}
		else 
		{
			//send change password form
			
			
		}
		
		return "change_password";
	}else {
		
		session.setAttribute("message", "You have entered Wrong OTP..Try again!!!");
		return "/varify_otp";
	}
	}
	
	//change password 
	
	@PostMapping("/change-password")
	public String change_password(@RequestParam("newpassword") String newpassword,HttpSession session) {
		
		String email = (String) session.getAttribute("email");
		User user = this.userRepository.getUserbyEmail(email);
		user.setUserpassword(this.bCryptPasswordEncoder.encode(newpassword));
		this.userRepository.save(user);
	
		return "redirect:/Login?change=password changed successfully...";
	}
}
