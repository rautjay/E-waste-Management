package com.project.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class Myconfig extends WebSecurityConfigurerAdapter {
@Autowired
 private LoginSuccessHandler loginSuccessHandler;
	
	@Bean
	public UserDetailsService getUserDetailsService() {
		return new userDetailsSeviceImpl();
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(this.getUserDetailsService());
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		
		return daoAuthenticationProvider;
	}

	/* configure methods */

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	  
		auth.authenticationProvider(authenticationProvider());
		
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
	
		.antMatchers("/admin/**").hasAuthority("ADMIN")
		.antMatchers("/user/**").hasAuthority("USER")
		.antMatchers("/vendor/**").hasAuthority("VENDOR")
		.antMatchers("/**").permitAll().anyRequest().authenticated()
		.and().
		formLogin().permitAll()
		.loginPage("/Login")
		.loginProcessingUrl("/do_login")
		.successHandler(loginSuccessHandler)
		.and()
		.logout().permitAll()
		.and()
		.exceptionHandling().accessDeniedPage("/403")
		.and().csrf().disable();
	}
	
	
	

	
}
