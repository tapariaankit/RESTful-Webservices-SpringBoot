package com.example.myapplication.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
public class UserController {
	
	@PostMapping
	public String createUser() {
		return "user created";
		
	}
	
	@GetMapping(value = "/getUser")
	public String getUser() {
		return "user retrieved";
	}
	
	@PutMapping
	public String updateUser() {
		return "user updated";
	}
	
	@DeleteMapping
	public String deleteUser() {
		return "user deleted";
	}
	
}
