package com.project.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.entities.Request;
import com.project.entities.User;


public interface RequestRepository extends JpaRepository<Request,Long> {
     
	@Query("from Request as R where R.user.userid=:userid")
	public Page<Request> findRequestByUser( @Param("userid") Long userid,Pageable pageable);
	
	
	//finding request of user according to state
	
	@Query(value ="select R from Request R  JOIN FETCH R.user U where U.state =:statename",countQuery = "select count(R) from Request R  JOIN  R.user U where U.state =:statename")
	public Page<Request> loadAllByState(@Param("statename") String statename,Pageable pageable);
	
	
	//finding request of user according to city
    @Query("select R from Request R JOIN FETCH R.user U join fetch U.userAddress ua where ua.city like %:keyword%")
	public List<Request> loadAllByCity(@Param("keyword") String keyword);
}


