package com.project.dao;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.entities.User;


public interface UserRepository extends JpaRepository<User,Long> {
	@Query("select u from User u where u.email=:email ")
	public User getUserbyEmail(@Param("email")String email);
	

	@Query( "select u from User u JOIN FETCH u.userroles ur join fetch ur.role r where r.name =:roleName" )
	 public List<User> findBySpecificRoles(@Param("roleName") String roleName);
	

	//fecthing user by request
    @Query("From Request as R where R.user =:Normaluser")
    public User getUserByRequest(@Param("Normaluser") User user); 
}
