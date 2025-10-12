package com.mediserve.userservice.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mediserve.userservice.entity.User;
import com.mediserve.userservice.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	 private final UserService userService;

	    public UserController(UserService userService) {
	        this.userService = userService;
	    }

	    // Create new user
	    @PostMapping
	    public User createUser(@RequestBody User user) {
	        return userService.createUser(user);
	    }

	    // Get all users
	    @GetMapping
	    public List<User> getAllUsers() {
	        return userService.getAllUsers();
	    }

	    // Get user by ID
	    @GetMapping("/{id}")
	    public User getUserById(@PathVariable Integer id) {
	        return userService.getUserById(id)
	                .orElseThrow(() -> new RuntimeException("User not found with id " + id));
	    }

	    // Update user
	    @PutMapping("/{id}")
	    public User updateUser(@PathVariable Integer id, @RequestBody User user) {
	        return userService.updateUser(id, user);
	    }

	    // Delete user
	    @DeleteMapping("/{id}")
	    public void deleteUser(@PathVariable Integer id) {
	        userService.deleteUser(id);
	    }
}
