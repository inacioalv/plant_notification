package com.inacioalves.microservice.notifications.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class fieldMessage implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String message;
	private String field;

}
