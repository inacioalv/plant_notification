package com.inacioalves.microservice.user_plant.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class UserNotFoundExeption extends Exception {

	private static final long serialVersionUID = 1L;

	public UserNotFoundExeption(Long id) {
		super("Plant not found with ID:"+id);
	}
	
	

}
