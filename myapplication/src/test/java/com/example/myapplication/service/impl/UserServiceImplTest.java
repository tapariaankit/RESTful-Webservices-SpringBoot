package com.example.myapplication.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.myapplication.dto.UserDto;
import com.example.myapplication.exceptions.UserServiceException;
import com.example.myapplication.model.UserEntity;
import com.example.myapplication.repository.UserRepository;
import com.example.myapplication.utils.ApplicationUtils;


public class UserServiceImplTest {
	
	@InjectMocks
	UserServiceImpl userService;
	
	@Mock
	UserRepository userRepository;
	
	@Mock
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Mock
	ApplicationUtils applicationUtils;
	
	UserEntity userEntity;
	
	String userId = "abcdefgh";
	String encryptedPassword = "fgdfh$dmjhgfo";
	
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		userEntity = new UserEntity();
		userEntity.setId(1L);
		userEntity.setFirstName("Ankit");
		userEntity.setLastName("Taparia");
		userEntity.setUserId(userId);
		userEntity.setEncryptedPassword(encryptedPassword);
		userEntity.setEmail("abc@def.com");
		userEntity.setEmailVerificationToken("7htnfhr758");
	}

	@Test
	final void testGetUser() {
		
		when(userRepository.findByEmail(anyString())).thenReturn(userEntity);

		UserDto userDto = userService.getUser("abc@def.com");

		assertNotNull(userDto);
		assertEquals("Ankit", userDto.getFirstName());
		assertEquals("Taparia", userDto.getLastName());
		assertEquals(userId, userDto.getUserId());
		assertEquals(encryptedPassword, userDto.getEncryptedPassword());
	}
	
	@Test
	final void testGetUser_UsernameNotFoundException() {
		when(userRepository.findByEmail(anyString())).thenReturn(null);

		assertThrows(UsernameNotFoundException.class,

				() -> {
					userService.getUser("test@test.com");
				}

		);
	}
	
	@Test
	final void testCreateUser_CreateUserServiceException()
	{
		when(userRepository.findByEmail(anyString())).thenReturn(userEntity);
		UserDto userDto = new UserDto();
		userDto.setFirstName("Ankit");
		userDto.setLastName("Taparia");
		userDto.setPassword("12345678");
		userDto.setEmail("test@test.com");
 	
		assertThrows(UserServiceException.class,

				() -> {
					userService.createUser(userDto);
				}

		);
	}
	
	@Test
	final void testCreateUser()
	{
		when(userRepository.findByEmail(anyString())).thenReturn(null);
		when(bCryptPasswordEncoder.encode(anyString())).thenReturn(encryptedPassword);
		when(applicationUtils.generateUserId(anyInt())).thenReturn(userId);
		when(userRepository.save(Mockito.any(UserEntity.class))).thenReturn(userEntity);
		
 		
		UserDto userDto = new UserDto();
		userDto.setId(1);
		userDto.setFirstName("Ankit");
		userDto.setLastName("Taparia");
		userDto.setPassword("12345678");
		userDto.setEmail("abc@def.com");

		UserDto storedUserDetails = userService.createUser(userDto);
		assertNotNull(storedUserDetails);
		assertEquals(userEntity.getFirstName(), storedUserDetails.getFirstName());
		assertEquals(userEntity.getLastName(), storedUserDetails.getLastName());
		assertNotNull(storedUserDetails.getUserId());
		verify(bCryptPasswordEncoder, times(1)).encode("12345678");
		verify(userRepository,times(1)).save(Mockito.any(UserEntity.class));
	}
}
