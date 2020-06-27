package com.example.myapplication.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.myapplication.dto.UserDto;
import com.example.myapplication.model.UserEntity;
import com.example.myapplication.repository.UserRepository;
import com.example.myapplication.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserRepository userRepository;
	
	@Override
	public UserDto createUser(UserDto user) {
		// TODO Auto-generated method stub
		UserEntity userEntity = new UserEntity();
		
		BeanUtils.copyProperties(user, userEntity);
		
		userEntity.setEncryptedPassword("test");
		userEntity.setUserId("testUserId");
		
		UserEntity storedUserDetails = userRepository.save(userEntity);
		
		UserDto returnValue = new UserDto();
		
		BeanUtils.copyProperties(storedUserDetails, returnValue);
		
		return returnValue;
	}

}
