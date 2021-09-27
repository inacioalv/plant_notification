package com.inacioalves.microservice.notifications.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class EmailDto {
	
	@NotBlank
	@Length(max = 80, min = 10)
	private String ownerRef;
	@NotBlank
	@Email
	private String emailTo;
	@NotBlank
	private String subject;
	
	

}