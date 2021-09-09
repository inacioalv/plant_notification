package com.inacioalves.microservice.notify_plant.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class PlantNotFoundExeption extends Exception {

	private static final long serialVersionUID = 1L;
	
	
	public PlantNotFoundExeption(Long id) {
		super("Plant not found with ID:"+id);
	}

}
