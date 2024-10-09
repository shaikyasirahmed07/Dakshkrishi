package com.thyme.services;

import java.util.List;

import com.thyme.entities.User;
import com.thyme.payloads.UserDto;

public interface UserService {
	
	UserDto createUser(UserDto user);
	UserDto updateUser(UserDto user, Integer userId);
	UserDto getUserById(Integer userId);
	UserDto authenticateUser(String userName, String password);
	List<UserDto> getAllUsers();
	
	void deleteUser(Integer userId);

}
		