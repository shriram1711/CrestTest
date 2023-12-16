package com.test.Chat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.test.Chat.model.User;
import com.test.Chat.request.LoginRequest;
import com.test.Chat.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/register")
	public User registerUser(@RequestBody User newUser) {
		return userService.registerUser(newUser);
	}
	
	@PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest) {
        boolean isAuthenticated = userService.authenticateUser(loginRequest.getUsername(), loginRequest.getPassword());
        if (isAuthenticated) {
            return "Login successful!";
        } else {
            return "Invalid credentials";
        }
    }
}
