package com.project.entities;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Request {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long requestId;
	
	@Column(length = 10)
	@Size(min = 10, max = 10, message = "Enter valid Mobile number!!")
	private String New_mobile;
	
	@Size(min=10, max=100,message = "Enter between 10 to 100 letters")
	private String Request;
	

	private Date Date;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private User user;
	

	public String Status;
	
	
	
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	
	
	/*
	 * @Override public String toString() { return "Request [requestId=" + requestId
	 * + ", New_mobile=" + New_mobile + ", Request=" + Request + ", Date=" + Date +
	 * ", user=" + user + ", Status=" + Status + "]"; }
	 * 
	 */
	
	public Request() {
		super();
	}
	
	
	public Request(Long requestId, String new_mobile, String request, java.util.Date date, User user) {
		super();
		this.requestId = requestId;
		New_mobile = new_mobile;
		Request = request;
		Date = date;
		this.user = user;
	}
	public Request(String status) {
		super();
		Status = status;
	}
	public Long getRequestId() {
		return requestId;
	}
	public void setRequestId(Long requestId) {
		this.requestId = requestId;
	}
	public String getNew_mobile() {
		return New_mobile;
	}
	public void setNew_mobile(String new_mobile) {
		New_mobile = new_mobile;
	}
	public String getRequest() {
		return Request;
	}
	public void setRequest(String request) {
		Request = request;
	}
	public Date getDate() {
		return Date;
	}
	public void setDate(Date date) {
		Date = date;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
	

}
