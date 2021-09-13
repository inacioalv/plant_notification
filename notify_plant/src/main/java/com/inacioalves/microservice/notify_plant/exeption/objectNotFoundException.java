package com.inacioalves.microservice.notify_plant.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class objectNotFoundException extends Exception  {

	
	private static final long serialVersionUID = 1L;
	
	public objectNotFoundException(String msg) {
		super(msg);
	}
	
	public objectNotFoundException(String msg, Throwable causa) {
		super(msg,causa);
	}

}