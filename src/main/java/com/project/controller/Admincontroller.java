package com.project.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
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
import com.project.entities.Request;
import com.project.entities.User;
import com.project.entities.userAddress;
import com.project.helper.RequestExportExcel;
import com.project.services.Userservice;

@Controller
@RequestMapping("/admin")
public class Admincontroller {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RequestRepository requestRepository;

	@Autowired
	private Userservice userservice;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	// showing Admin home page

	@GetMapping("/index")
	public String successlogin() {

		return "admin/Admin_dashboard";
	}

	@GetMapping("/users_Request/{page}")
	public String show_all_requests(@PathVariable("page") Integer page , Model  model,  Principal principal) {

		// requests
		String username = principal.getName();
		System.out.println("username:" + username);
		User user = this.userRepository.getUserbyEmail(username);
	    Pageable pageable = PageRequest.of(page,4);
      Page<Request> RequestList = requestRepository.loadAllByState(user.getState(), pageable);
       
      model.addAttribute("Requestlist", RequestList); 
		model.addAttribute("currentpage", page);
		model.addAttribute("totalpages", RequestList.getTotalPages());
		return "admin/allusers_request";
	}	
			


	// onclick on View Status link
	@PostMapping("/{requestId}/request_details")
	public String show_requestdetails(@PathVariable("requestId") Long requestId, Model model, Principal principal) {

		System.out.println("requestId:" + requestId);
		Request request = requestRepository.findById(requestId).get();
		User Normaluser = request.getUser();
		model.addAttribute("request", request);
		model.addAttribute("Normaluser", Normaluser);

		return "admin/request_details";
	}

	// process status to user
	@PostMapping("/process_request")
	public String Process_status(@Valid @ModelAttribute Request request, BindingResult result,
			@RequestParam(name = "Date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date Date, Model model,
			Principal principal) {

		request.setDate(Date);
		request.setUser(requestRepository.getOne(request.getRequestId()).getUser());
		this.requestRepository.save(request);

		System.out.println("added to database");

		return "admin/Admin_dashboard";
	}
	
	//creating report of a request
	
	@GetMapping("/{requestId}/create_report")
	public String Create_report(@PathVariable("requestId") Long requestId, Model model, Principal principal) {
	
		System.out.println("requestId:" + requestId);
		Request request = requestRepository.findById(requestId).get();
		User Normaluser = request.getUser();
		model.addAttribute("request", request);
		model.addAttribute("Normaluser", Normaluser);
		
		return "admin/create_report"; 
	}

	
	@PostMapping("/{requestId}/export_report")
	public void Export_report(@PathVariable("requestId") Long requestId,HttpServletResponse response) throws IOException {
		
		response.setContentType("application/octet-stram");
		String headerkey ="Content-Disposition";
		String headervalue = "attachment; filename= Request_info.xls";
		response.setHeader(headerkey, headervalue);
		  User Normaluser = this.requestRepository.getById(requestId).getUser();
		  List<Request> RequestList = Normaluser.getRequests(); 
		RequestExportExcel exp = new RequestExportExcel(RequestList);
		exp.export(response);
		
	}
	
	
}
