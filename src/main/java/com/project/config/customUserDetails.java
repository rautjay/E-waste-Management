package com.project.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.project.entities.Role;
import com.project.entities.User;
import com.project.entities.User_Role;

public class customUserDetails implements UserDetails {
	private User user;

	public customUserDetails(User user) {
		super();
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
	 Set<User_Role> userroles = user.getUserroles();
	 
     Set<SimpleGrantedAuthority> authorities = new HashSet<>();

	userroles.forEach(User_Role ->{
		authorities.add(new SimpleGrantedAuthority(User_Role.getRole().getName()));
	});
	  
		
	return authorities;
	}
	


	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getUserpassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return user.isEnabled();
	}

}
