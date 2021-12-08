package com.project.entities;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class User {
	@NotBlank(message = "This field can not be Empty!!")
	@Size(min = 3, max = 8, message = "must be atleast 3-8 charachters!!")
	private String userFirstname;

	@NotBlank(message = "This field can not be Empty!!")
	@Size(min = 3, max = 8, message = "must be atleast 3-8 charachters!!")
	private String userLastname;

	private Date userDOB;

	@NotBlank(message = "This field can not be Empty!!")
	private String usergender;

	@Column(length = 10, name = "user_mobile", unique = true)
	@NotBlank(message = "This field can not be Empty!!")
	@Size(min = 10, max = 10, message = "Enter valid Mobile number!!")
	private String usermobile;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true, length = 11, name = "user_id")
	private Long userid;

	@Column(name = "user_password", unique = true)
	@Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{6,}$", message = "Min 6 charachters ,One Special charachter,one digit and letter required!!")
	private String userpassword;

	@Column(unique = true)
	@Pattern(regexp = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$", message = "please add valid Email")
	private String email;

	@NotBlank(message = "This field can not be Empty!!")
	private String state;

	private boolean enabled = true;

	@OneToOne(cascade = CascadeType.ALL)
	@Valid
	private userAddress userAddress;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Request> requests = new ArrayList<>();

	// User has a many Roles
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user")
	@JsonIgnore
	private Set<User_Role> userroles = new HashSet<>();

	
	  
	
	  @Override public String toString() { return "User [userFirstname=" +
	  userFirstname + ", userLastname=" + userLastname + ", userDOB=" + userDOB +
	  ", usergender=" + usergender + ", usermobile=" + usermobile + ", userid=" +
	  userid + ", userpassword=" + userpassword + ", email=" + email + ", state=" +
	  state + ", enabled=" + enabled + ", userAddress=" + userAddress +
	  ", requests=" + requests + ", userroles=" + userroles + "]"; }
	 
	  
	 

	public User() {
		super();
	}

	// Constructor with fields
	public User(
			@NotBlank(message = "This field can not be Empty!!") @Size(min = 3, max = 8, message = "must be atleast 3-8 charachters!!") String userFirstname,
			@NotBlank(message = "This field can not be Empty!!") @Size(min = 3, max = 8, message = "must be atleast 3-8 charachters!!") String userLastname,
			Date userDOB, @NotBlank(message = "This field can not be Empty!!") String usergender,
			@NotBlank(message = "This field can not be Empty!!") @Size(min = 10, max = 10, message = "Enter valid Mobile number!!") String usermobile,
			Long userid,
			@Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{6,}$", message = "Min 6 charachters ,One Special charachter,one digit and letter required!!") String userpassword,
			@Pattern(regexp = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$", message = "please add valid Email") String email,
			@NotBlank(message = "This field can not be Empty!!") String state, boolean enabled,
			com.project.entities.@Valid userAddress userAddress, List<Request> requests, Set<User_Role> userroles) {
		super();
		this.userFirstname = userFirstname;
		this.userLastname = userLastname;
		this.userDOB = userDOB;
		this.usergender = usergender;
		this.usermobile = usermobile;
		this.userid = userid;
		this.userpassword = userpassword;
		this.email = email;
		this.state = state;
		this.enabled = enabled;
		this.userAddress = userAddress;
		this.requests = requests;
		this.userroles = userroles;
	}

	public String getUserFirstname() {
		return userFirstname;
	}

	public void setUserFirstname(String userFirstname) {
		this.userFirstname = userFirstname;
	}

	public String getUserLastname() {
		return userLastname;
	}

	public void setUserLastname(String userLastname) {
		this.userLastname = userLastname;
	}

	public Date getUserDOB() {
		return userDOB;
	}

	public void setUserDOB(Date userDOB) {
		this.userDOB = userDOB;
	}

	public String getUsergender() {
		return usergender;
	}

	public void setUsergender(String usergender) {
		this.usergender = usergender;
	}

	public String getUsermobile() {
		return usermobile;
	}

	public void setUsermobile(String usermobile) {
		this.usermobile = usermobile;
	}

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public String getUserpassword() {
		return userpassword;
	}

	public void setUserpassword(String userpassword) {
		this.userpassword = userpassword;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public userAddress getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(userAddress userAddress) {
		this.userAddress = userAddress;
	}

	public List<Request> getRequests() {
		return requests;
	}

	public void setRequests(List<Request> requests) {
		this.requests = requests;
	}

	public Set<User_Role> getUserroles() {
		return userroles;
	}

	public void setUserroles(Set<User_Role> userroles) {
		this.userroles = userroles;
	}

}
