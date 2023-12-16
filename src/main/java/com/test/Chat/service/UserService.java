package com.test.Chat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.Chat.model.User;
import com.test.Chat.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public boolean authenticateUser(String username, String password) {
		User user = userRepository.findByUsername(username);
		return user != null && password.equals(user.getPassword());
	}

	public User registerUser(User newUser) {
		return userRepository.save(newUser);
	}

}
