package com.example.myapplication.exceptions;

public class UserServiceException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6952091137769658171L;

	public UserServiceException(String message) {
		super(message);
	}
}
