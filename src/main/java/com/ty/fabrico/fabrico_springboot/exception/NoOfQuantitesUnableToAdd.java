package com.ty.fabrico.fabrico_springboot.exception;

public class NoOfQuantitesUnableToAdd extends RuntimeException{

	private String message ="No of Quantites Not Available";
	
	public NoOfQuantitesUnableToAdd(String message) {
		super();
		this.message = message;
	}

	public NoOfQuantitesUnableToAdd() {
		
	}


	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return message;
	}
}
