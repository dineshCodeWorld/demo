package com.infy.tesla.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.infy.tesla.dto.ChangeRoleDto;
import com.infy.tesla.dto.DeleteDto;
import com.infy.tesla.dto.PasswordDto;
import com.infy.tesla.dto.UpdateDto;
import com.infy.tesla.dto.UserDto;
import com.infy.tesla.dto.UserResponse;
import com.infy.tesla.entity.User;
import com.infy.tesla.event.RegistrationCompleteEvent;
import com.infy.tesla.exception.UserNotFoundException;
import com.infy.tesla.service.UserService;

@RestController
@CrossOrigin(origins = "*")
public class UserController
{
	@Autowired
	private UserService userService;
	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;
	
	@GetMapping("/home")
   public String home()
   {
	   return "Home Page";
	   
   }
	@PostMapping("/register")
	public String register(@RequestBody UserDto userDto,final HttpServletRequest request) throws UserNotFoundException
	{
	    Boolean	response = userService.register(userDto);
	    if(response) {
	    	User user = userService.findByEmail(userDto.getEmail());
	    	applicationEventPublisher.publishEvent(new RegistrationCompleteEvent(user,applicationUrl(request) ));
		    return "verificatin link sent to your mail";
	    }return "user alrady registered";
	}
	
	private String applicationUrl(HttpServletRequest request) {
        return "http://"+ request.getServerName() +":"+request.getServerPort() + request.getContextPath();
	}
	
	@GetMapping("/validateToken")
	public String validateToken(@RequestParam("token") String token) throws UserNotFoundException
	{
		return userService.validateToken(token);
	}
	
//-------------------------------------------------------------------	
	
	@PostMapping("/resendToken")
	public String resendToken(@RequestBody DeleteDto deleteDto) throws UserNotFoundException
	{
		return userService.resendToken(deleteDto);
	}
	
//-------------------------------------------------------------------	
	@PostMapping("/resetPassword")
	public String resetPassword(@RequestBody PasswordDto passwordDto) throws UserNotFoundException
	{
		return userService.resetPassword(passwordDto);
	}
	@GetMapping("/validatePasswordResetToken")
	public String validatePasswordResetToken(@RequestParam("token") String token) throws UserNotFoundException
	{
		return userService.validatePasswordResetToken(token);
	}
	
//-------------------------------------------------------------------	
	
	@PostMapping("/changePassword")
	public String changePassword(@RequestBody PasswordDto passwordDto) throws UserNotFoundException
	{
		return userService.changePassword(passwordDto);
	}
	
//-------------------------------------------------------------------	
	
	@GetMapping("/getUserList")
	public List<UserResponse> getAllUsersList() throws UserNotFoundException
	{
		return userService.getAllUsersList();
	}
	
	@PutMapping("/updateProfile")
	public String updateProfile(@RequestBody UpdateDto updateDto) throws UserNotFoundException
	{
		return userService.updateProfile(updateDto);
	}
	@PostMapping("/deleteUser")
	public String deleteUser(@RequestBody DeleteDto deleteDto) throws UserNotFoundException
	{
		return  userService.deleteUser(deleteDto);
	}
//----------------------------------------------------------------------------
	
	@PostMapping("/changeUserRole")
	public String changeRole(@RequestBody ChangeRoleDto changeRoleDto) throws UserNotFoundException
	{
		return userService.changeRole(changeRoleDto);
	}
}
