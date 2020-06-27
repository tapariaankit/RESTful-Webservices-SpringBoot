package com.example.myapplication.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.myapplication.dto.UserDto;
import com.example.myapplication.model.UserEntity;
import com.example.myapplication.repository.UserRepository;
import com.example.myapplication.service.UserService;
import com.example.myapplication.utils.ApplicationUtils;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ApplicationUtils applicationUtils;
	
	@Override
	public UserDto createUser(UserDto user) {
		// TODO Auto-generated method stub
		 
		if(userRepository.findByEmail(user.getEmail()) != null) 
			throw new RuntimeException("Record already exists");
		
		
		UserEntity userEntity = new UserEntity();
		
		BeanUtils.copyProperties(user, userEntity);
		
		userEntity.setEncryptedPassword("test");
		userEntity.setUserId(applicationUtils.generateUserId(30));
		
		UserEntity storedUserDetails = userRepository.save(userEntity);
		
		UserDto returnValue = new UserDto();
		
		BeanUtils.copyProperties(storedUserDetails, returnValue);
		
		return returnValue;
	}

}
