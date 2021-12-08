package com.project.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class userAddress {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
	
	@NotBlank(message = "This field can not be Empty!!")
	private String flatno;
	
	@NotBlank(message = "This field can not be Empty!!")
	private String streetname;
	
	@NotBlank(message = "This field can not be Empty!!")
	private String landmark;
	
	@NotBlank(message = "This field can not be Empty!!")
	private String city;
	


	public userAddress(int id, @NotBlank(message = "This field can not be Empty!!") String flatno,
			@NotBlank(message = "This field can not be Empty!!") String streetname,
			@NotBlank(message = "This field can not be Empty!!") String landmark,
			@NotBlank(message = "This field can not be Empty!!") String city)
		 {
		super();
		this.id = id;
		this.flatno = flatno;
		this.streetname = streetname;
		this.landmark = landmark;
		this.city = city;
		
	}

	public userAddress() {
		super();
	}

	public int getAddressid() {
		return id;
	}

	public void setAddressid(int addressid) {
		this.id = addressid;
	}

	public String getFlatno() {
		return flatno;
	}

	public void setFlatno(String flatno) {
		this.flatno = flatno;
	}

	public String getStreetname() {
		return streetname;
	}

	public void setStreetname(String streetname) {
		this.streetname = streetname;
	}

	public String getLandmark() {
		return landmark;
	}

	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	
	@Override
	public String toString() {
		return "userAddress [addressid=" + id + ", flatno=" + flatno + ", streetname=" + streetname
				+ ", landmark=" + landmark + ", city=" + city + "]";
	}

	

}
