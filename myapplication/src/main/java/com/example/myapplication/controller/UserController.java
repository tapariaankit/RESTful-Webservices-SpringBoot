package com.example.myapplication.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE, 
			MediaType.APPLICATION_XML_VALUE})
	public UserDetailsResponseModel createUser(@RequestBody UserDetailsRequestModel userDetails) {
		
		UserDetailsResponseModel returnValue = new UserDetailsResponseModel();
		
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(userDetails, userDto);
		
		UserDto createdUser = userService.createUser(userDto);
		BeanUtils.copyProperties(createdUser, returnValue);
		
		return returnValue;
		
	}
	
	@GetMapping(path="/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, 
			MediaType.APPLICATION_XML_VALUE})
	public UserDetailsResponseModel getUser(@PathVariable String id) {
		
		UserDetailsResponseModel returnValue = new UserDetailsResponseModel();
		UserDto userDto = userService.getUserByUserId(id);
		BeanUtils.copyProperties(userDto, returnValue);
		return returnValue;
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
