package com.example.myapplication.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.myapplication.model.UserEntity;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {

	// find user by email address
	UserEntity findByEmail(String email);
	UserEntity findByUserId(String userId);
}
