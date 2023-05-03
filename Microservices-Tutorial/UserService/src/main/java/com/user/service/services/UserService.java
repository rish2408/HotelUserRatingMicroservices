package com.user.service.services;

import java.util.List;

import com.user.service.entities.Rating;
import com.user.service.entities.User;

public interface UserService {

	// Create User
	
	public User saveUser(User user);
	
	// Get User
	
	public User getUser(String userId);
	
	// Get All User
	
	public List<User> getAllUser();
	
	// Update User

	
	
	// Delete User
	
	
	
}
