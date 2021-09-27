package com.inacioalves.microservice.notify_plant.exeption;


public class objectNotFoundException extends RuntimeException   {

	
	private static final long serialVersionUID = 1L;
	
	public objectNotFoundException(String msg) {
		super(msg);
	}
	
	public objectNotFoundException(String msg, Throwable causa) {
		super(msg,causa);
	}

}