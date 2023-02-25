package com.ty.fabrico.fabrico_springboot.exception;

public class NoSuchUsernameFoundException extends RuntimeException{

	String message="No User Name Found";

	public NoSuchUsernameFoundException(String message) {
		super();
		this.message = message;
	}

	public NoSuchUsernameFoundException() {
		super();
	
	}

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return message;
	}

	
}
