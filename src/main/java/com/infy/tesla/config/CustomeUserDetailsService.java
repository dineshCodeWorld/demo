package com.infy.tesla.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.infy.tesla.entity.User;
import com.infy.tesla.repository.UserRepository;
@Service
public class CustomeUserDetailsService implements UserDetailsService
{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
           User user = userRepository.findByFirstName(username);
           if(!(user==null))
           {
           return new CustomUserDetails(user);
           }else
           {
        	   throw new UsernameNotFoundException("user not found");
           }
	}
   
}
