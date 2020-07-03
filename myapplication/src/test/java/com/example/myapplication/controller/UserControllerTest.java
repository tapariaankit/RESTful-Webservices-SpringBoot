package com.example.myapplication.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.example.myapplication.dto.UserDto;
import com.example.myapplication.model.response.UserDetailsResponseModel;
import com.example.myapplication.service.impl.UserServiceImpl;

public class UserControllerTest {

	@InjectMocks
	UserController userController;

	@Mock
	UserServiceImpl userService;

	UserDto userDto;

	final String USER_ID = "abcdefghij";

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);

		userDto = new UserDto();
		userDto.setFirstName("Ankit");
		userDto.setLastName("Taparia");
		userDto.setEmail("abc@def.com");
		userDto.setUserId(USER_ID);
		userDto.setEncryptedPassword("fgdfh$dmjhgfo");

	}

	@Test
	final void testGetUser() {
		when(userService.getUserByUserId(anyString())).thenReturn(userDto);

		UserDetailsResponseModel userDetailsResponseModel = userController.getUser(USER_ID);

		assertNotNull(userDetailsResponseModel);
		assertEquals(USER_ID, userDetailsResponseModel.getUserId());
		assertEquals(userDto.getFirstName(), userDetailsResponseModel.getFirstName());
		assertEquals(userDto.getLastName(), userDetailsResponseModel.getLastName());
	}
}
