package com.inacioalves.microservice.notifications.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;


import lombok.Data;

@Data
public class EmailDto {
	
	@NotBlank
	@Length(max = 80, min = 10)
	private String ownerRef;
	@NotBlank
	@Email
	private String emailFrom;
	@NotBlank
	private String subject;
	
	

}