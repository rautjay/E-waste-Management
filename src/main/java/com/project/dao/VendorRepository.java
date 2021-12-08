package com.project.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.entities.User;
import com.project.entities.Vendor;

public interface VendorRepository extends JpaRepository<Vendor, Long> 
{
	@Query("select v from Vendor v where v.vendorid=:vendorid ")
	public User getUserbyEmail(@Param("vendorid")String vendorid);
}
