package com.infy.tesla.service;

import java.util.List;

import com.infy.tesla.dto.ChangeRoleDto;
import com.infy.tesla.dto.DeleteDto;
import com.infy.tesla.dto.PasswordDto;
import com.infy.tesla.dto.UpdateDto;
import com.infy.tesla.dto.UserDto;
import com.infy.tesla.dto.UserResponse;
import com.infy.tesla.entity.User;
import com.infy.tesla.exception.UserNotFoundException;

public interface UserService {

	Boolean register(UserDto userDto) throws UserNotFoundException;

	List<UserResponse> getAllUsersList() throws UserNotFoundException;

    String updateProfile(UpdateDto updateDto) throws UserNotFoundException;

	String deleteUser(DeleteDto deleteDto) throws UserNotFoundException;

	User findByEmail(String email);

    String validateToken(String token) throws UserNotFoundException;

    String resendToken(DeleteDto deleteDto) throws UserNotFoundException;

	String resetPassword(PasswordDto passwordDto) throws UserNotFoundException;

	String validatePasswordResetToken(String token) throws UserNotFoundException;

	String changePassword(PasswordDto passwordDto) throws UserNotFoundException;

	String changeRole(ChangeRoleDto changeRoleDto) throws UserNotFoundException;

}
