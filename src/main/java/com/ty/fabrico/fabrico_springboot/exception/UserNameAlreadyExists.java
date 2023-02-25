package com.ty.fabrico.fabrico_springboot.exception;

public class UserNameAlreadyExists extends RuntimeException {

	String message;

	public UserNameAlreadyExists(String message) {
		super();
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}
	
	
	
	
}
