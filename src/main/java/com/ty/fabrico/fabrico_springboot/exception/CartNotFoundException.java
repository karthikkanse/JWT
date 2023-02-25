package com.ty.fabrico.fabrico_springboot.exception;

public class CartNotFoundException extends RuntimeException {

	String message ="Cart not found for customer";

	

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return message;
	}



	public CartNotFoundException() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	
}
