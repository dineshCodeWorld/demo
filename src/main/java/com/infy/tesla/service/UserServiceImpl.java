package com.infy.tesla.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.infy.tesla.dto.ChangeRoleDto;
import com.infy.tesla.dto.DeleteDto;
import com.infy.tesla.dto.PasswordDto;
import com.infy.tesla.dto.UpdateDto;
import com.infy.tesla.dto.UserDto;
import com.infy.tesla.dto.UserResponse;
import com.infy.tesla.entity.Password;
import com.infy.tesla.entity.Token;
import com.infy.tesla.entity.User;
import com.infy.tesla.exception.UserNotFoundException;
import com.infy.tesla.repository.PasswordRepository;
import com.infy.tesla.repository.TokenRepository;
import com.infy.tesla.repository.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService
{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private TokenRepository tokenRepository;
    @Autowired
    private com.infy.tesla.event.EventListener eventListener;
    @Autowired
    private PasswordRepository passwordRepository;
	
	@Override
	public Boolean register(UserDto userDto) throws UserNotFoundException 
	{
		try {
	   Optional<User> user = userRepository.findByEmail(userDto.getEmail());
	   if(!user.isPresent())
	   {	
	   User user1 = new User();
	   user1.setFirstName(userDto.getFirstName());
	   user1.setLastName(userDto.getLastName());
	   user1.setEmail(userDto.getEmail());
	   user1.setPassword(passwordEncoder.encode(userDto.getPassword()));
	   user1.setRole("USER");
	   userRepository.save(user1);
	   return  true;
	   }return false;
		}catch (Exception e) {
			throw new UserNotFoundException("something went wrong");
		}
	}
	
//-----------------------------------------------------------------	

	@Override
	public List<UserResponse> getAllUsersList() throws UserNotFoundException 
	{
		try {
        List<User> users = userRepository.findAll();
	    List<UserResponse> userResponses = new ArrayList<>();
        for(User user2:users)
        {
            UserResponse userResponse = new UserResponse();
        	    userResponse.setUserId(user2.getUserId());
        		userResponse.setFirstName(user2.getFirstName());
        		userResponse.setLastName(user2.getLastName());
        		userResponse.setEmail(user2.getEmail());
        		userResponse.setRole(user2.getRole());
        		userResponse.setStatus(user2.getStatus());
                userResponses.add(userResponse);
        }
        return userResponses;  
		}catch (Exception e) {
			throw new UserNotFoundException("something went wrong");
		}
	}
	
//--------------------------------------------------------------------	

	@Override
	public String updateProfile(UpdateDto updateDto) throws UserNotFoundException 
	{
		try {
		Optional<User> user = userRepository.findById(updateDto.getUserId());
		if(user.isPresent())
		{
			if(user.get().getStatus().equals("ACTIVE"))
			{
			user.get().setFirstName(updateDto.getFirstName());
			user.get().setLastName(updateDto.getLastName());
			user.get().setEmail(updateDto.getEmail());
			userRepository.save(user.get());
			return "profile updated successfully";
		}
		return "account disabled";
		}return "user not present";
		}catch (Exception e) {
			throw new UserNotFoundException("something went wrong");
		}
	}
	
//----------------------------------------------------------------------	

	@Override
	public String deleteUser(DeleteDto deleteDto) throws UserNotFoundException
	{
		try {
		      Optional<User> user = userRepository.findByEmail(deleteDto.getEmail());
		      if(user.isPresent())
		      {
		    	 Boolean result = passwordEncoder.matches(deleteDto.getPassword(), user.get().getPassword());
		    	 if(result)
		    	 {
		    		 Optional<Token> token = tokenRepository.findByUserId(user.get().getUserId());
		    		 if(token.isPresent()) {tokenRepository.delete(token.get());}
		    		 Optional<Password> password = passwordRepository.findByUserId(user.get().getUserId());
		    		 if(password.isPresent()) { passwordRepository.delete(password.get());}
		    		 
		    		 userRepository.delete(user.get());
		    		 return "user deleted successfully";
		    	 }
		    	 return "incorrect password";
		      }return "user not present";
		} catch (Exception e) {
			throw new UserNotFoundException("something went wrong");
		}
      
	}

//----------------------------------------------------------------------	
	
	@Override
	public User findByEmail(String email) {
      return userRepository.findByEmail(email).get();
	}

	@Override
	public String validateToken(String token) throws UserNotFoundException {
		
		try {
      Optional<Token> token2 = tokenRepository.findByToken(token);
      if(token2.isPresent())
      {
    	  if(!(token2.get().getExpiryDate().getTime()-Calendar.getInstance().getTime().getTime()<=0))
    	  {
    		  User user = token2.get().getUser();
    		  user.setStatus("ACTIVE");
    		  userRepository.save(user);
    		  return "user validation succsessfull";
    	  }
    	  tokenRepository.delete(token2.get());
    	  return "token expired";
      }return "invalid token";
		} catch (Exception e) {
			throw new UserNotFoundException("something went wrong");
		}
	}
	
//-------------------------------------------------------------------------	

	@Override
	public String resendToken(DeleteDto deleteDto) throws UserNotFoundException {
		try {
       Optional<User> user =  userRepository.findByEmail(deleteDto.getEmail());
       if(user.isPresent())
       {
    	   if(user.get().getStatus().equalsIgnoreCase("DISABLED"))
    	   {
    	   if(passwordEncoder.matches(deleteDto.getPassword(), user.get().getPassword()))
    	   {
    		  Optional<Token> token = tokenRepository.findByUserId(user.get().getUserId());
    		  if(token.isPresent())
    		  {
    			  tokenRepository.delete(token.get());
    			  String token2 = UUID.randomUUID().toString();
    			  Token token3 = new Token(token2,user.get());
    			  tokenRepository.save(token3);
    			  String url = "http://localhost:8080/validateToken?token="+token2;
    		      String body = "Please click on the link to verify your account: " + url;
    			  eventListener.sendSimpleMail(user.get().getEmail(),"verification Link" , body);
    			  return "verificatin link sent to your mail";
    		  }return "invalid credentials";
    	   }return "invalid credentials";
       }return "user is already verified";
       }return "user is not present";
		} catch (Exception e) {
			throw new UserNotFoundException("something went wrong");
		}
	}
	
//------------------------------------------------------------------------------	

	@Override
	public String resetPassword(PasswordDto passwordDto) throws UserNotFoundException {
		try {
       Optional<User> user = userRepository.findByEmail(passwordDto.getEmail());
       if(user.isPresent())
       {
    	   if(user.get().getStatus().equals("ACTIVE"))
    	   {
    	   String token = UUID.randomUUID().toString();
    	   String newPassword = passwordEncoder.encode(passwordDto.getNewPassword());
    	   Password password = new Password(user.get().getPassword(),newPassword,token,user.get());
    	   passwordRepository.save(password);
		   String url = "http://localhost:8080/validatePasswordResetToken?token="+token;
		   String body = "Please click on the link to reset your password: " + url;
			  eventListener.sendSimpleMail(user.get().getEmail(),"verification Link" , body);
			  return "verificatin link sent to your mail";
       }return "account disabled";
       }return "invalid credentials";
       } catch (Exception e) {
			throw new UserNotFoundException("something went wrong");
		}
	}

	@Override
	public String validatePasswordResetToken(String token) throws UserNotFoundException
	{
		try {
      Optional<Password> password = passwordRepository.findByToken(token);
      if(password.isPresent())
      {
    	  if(!(password.get().getExpiryDate().getTime()-Calendar.getInstance().getTime().getTime()<=0))
    	  {
    		  User user = password.get().getUser();
    		  user.setPassword(password.get().getNewPassword());
    		  password.get().setStatus("APPROVED");
    		  userRepository.save(user);
    		  return "password reset successfull";
    	  }return "token expired";
      }return "invalid token";
		} catch (Exception e) {
			throw new UserNotFoundException("something went wrong");
		}
	}

//-----------------------------------------------------------------------------------	
	
	@Override
	public String changePassword(PasswordDto passwordDto) throws UserNotFoundException {
		try {
        Optional<User> user = userRepository.findByEmail(passwordDto.getEmail());
        if(user.isPresent())
        {
          if(user.get().getStatus().equals("ACTIVE"))
          {
        	if(passwordEncoder.matches(passwordDto.getOldPassword(), user.get().getPassword()))
        	{
        		user.get().setPassword(passwordEncoder.encode(passwordDto.getNewPassword()));
        		userRepository.save(user.get());
        		return "password changed successfully";
        	}return "invalid old password";
        }return "account disabled";
        }return "user not found";
        } catch (Exception e) {
			throw new UserNotFoundException("something went wrong");
		}
	}
//---------------------------------------------------------------------------------------

	@Override
	public String changeRole(ChangeRoleDto changeRoleDto) throws UserNotFoundException {
		try {
               Optional<User> user = userRepository.findByEmail(changeRoleDto.getEmail());
               if(user.isPresent())
               {
        	   user.get().setRole(changeRoleDto.getRole());
        	   userRepository.save(user.get());
        	   return "role changed successfull";
               }return "user not found";
        } catch (Exception e) {
			throw new UserNotFoundException("something went wrong");
		}
	}
//---------------------------------------------------------------------------------------	
	
}
