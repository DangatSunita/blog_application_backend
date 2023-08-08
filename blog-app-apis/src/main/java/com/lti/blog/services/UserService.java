package com.lti.blog.services;

import java.util.List;

import com.lti.blog.paylods.UserDto;

public interface UserService {
 
	UserDto createUser(UserDto user);
	UserDto updateUser(UserDto user, Integer userId);
	UserDto getUserById(Integer userId);
	List<UserDto> getAllUsers();
	void deleteUser(Integer userId);
	
	UserDto registerNewUser(UserDto user);
}
