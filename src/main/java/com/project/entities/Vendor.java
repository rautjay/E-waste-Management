package com.project.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Pattern;
@Entity
public class Vendor implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long v_Id;
	
	@Column(name = "vendor_name", length = 100)
	private String vendorname;
	@Column(name = "type")
	private String vendortype;
	@Column(name = "vendor_mobile", length = 12)
	private String vendormobile;
	@Column(name = "vendor_address", length = 1500)
	private String vendorAddress;
   
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Pattern(regexp = "(\"/^.{5}$/\", $array)")
	private String vendorid; 
	
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{6,}$")
	private String vendor_password;
	

	public Vendor() {
		super();
	}


	public Long getV_Id() {
		return v_Id;
	}


	public void setV_Id(Long v_Id) {
		this.v_Id = v_Id;
	}


	public String getVendorname() {
		return vendorname;
	}


	public void setVendorname(String vendorname) {
		this.vendorname = vendorname;
	}


	public String getVendortype() {
		return vendortype;
	}


	public void setVendortype(String vendortype) {
		this.vendortype = vendortype;
	}


	public String getVendormobile() {
		return vendormobile;
	}


	public void setVendormobile(String vendormobile) {
		this.vendormobile = vendormobile;
	}


	public String getVendorAddress() {
		return vendorAddress;
	}


	public void setVendorAddress(String vendorAddress) {
		this.vendorAddress = vendorAddress;
	}


	public String getVendorid() {
		return vendorid;
	}


	public void setVendorid(String vendorid) {
		this.vendorid = vendorid;
	}


	public String getVendor_password() {
		return vendor_password;
	}


	public void setVendor_password(String vendor_password) {
		this.vendor_password = vendor_password;
	}


	public Vendor(Long v_Id, String vendorname, String vendortype, String vendormobile, String vendorAddress,
			@Pattern(regexp = "(\"/^.{5}$/\", $array)") String vendorid,
			@Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{6,}$") String vendor_password) {
		super();
		this.v_Id = v_Id;
		this.vendorname = vendorname;
		this.vendortype = vendortype;
		this.vendormobile = vendormobile;
		this.vendorAddress = vendorAddress;
		this.vendorid = vendorid;
		this.vendor_password = vendor_password;
	}

	

}
