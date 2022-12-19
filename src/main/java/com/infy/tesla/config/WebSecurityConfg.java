package com.infy.tesla.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class WebSecurityConfg extends WebSecurityConfigurerAdapter
{
	@Autowired
	private CustomeUserDetailsService customeUserDetailsService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
         auth.userDetailsService(customeUserDetailsService);
	}



	@Override
	protected void configure(HttpSecurity http) throws Exception {
	      http.cors().and().csrf().disable();
	      http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.ALWAYS);
	       http.authorizeHttpRequests().antMatchers("/home").hasAuthority("ADMIN")
	                                   .antMatchers("/changeUserRole/**","/deleteUser","/getUserList","/register","/validateToken","/resendToken","/resetPassword","/validatePasswordResetToken","/changePassword","/updateProfile").permitAll()
	                                   .anyRequest().authenticated()
	                                   .and().httpBasic().and().logout().permitAll();
	}



@Bean
  public PasswordEncoder passwordEncoder()
  {
	  return new BCryptPasswordEncoder(11);
  }


  
}
