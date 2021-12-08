package com.project.controller;

import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.dao.UserRepository;
import com.razorpay.Order;
import com.razorpay.*;
import com.razorpay.RazorpayClient;

@Controller
public class Paymentcontroller {

	@Autowired
	private UserRepository userRepository;
	
	//payment create order
	
	@PostMapping("/user/create_order")
	@ResponseBody
	public String create_order(@RequestBody Map<String,Object> data) throws Exception {
	     
		System.out.println(data);
		int amt = Integer.parseInt( data.get("amount").toString());
		
	var client = new RazorpayClient("rzp_test_uUcNFVxM3dHSQp", "oczE3v5OQyDpZeKv4vM0rzcA");
		 
	JSONObject Job = new JSONObject();
	Job.put("amount", amt*100);
	Job.put("currency","INR");
	Job.put("receipt","txn_456738");
	
	//creating order
	Order order = client.Orders.create(Job);
		System.out.println(order);
		
	//save this order info in database
		return order.toString();
	}
	
	
}
