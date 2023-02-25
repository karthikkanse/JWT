package com.ty.fabrico.fabrico_springboot.exception;

public class PasswordIncorrectException extends RuntimeException{

	String message="Invalid Password";

	public PasswordIncorrectException(String message) {
		super();
		this.message = message;
	}

	public PasswordIncorrectException() {
		super();
	}

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return message;
	}


	
}
