package com.example.myapplication.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.myapplication.dto.UserDto;
import com.example.myapplication.model.request.UserDetailsRequestModel;
import com.example.myapplication.model.response.UserDetailsResponseModel;
import com.example.myapplication.service.impl.UserServiceImpl;

@RestController
@RequestMapping("users")
public class UserController {
	
	@Autowired
	UserServiceImpl userService;
	
	@PostMapping
	public UserDetailsResponseModel createUser(@RequestBody UserDetailsRequestModel userDetails) {
		
		UserDetailsResponseModel returnValue = new UserDetailsResponseModel();
		
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(userDetails, userDto);
		
		UserDto createdUser = userService.createUser(userDto);
		BeanUtils.copyProperties(createdUser, returnValue);
		
		
		return returnValue;
		
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
