package com.inacioalves.microservice.notifications.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.inacioalves.microservice.notifications.model.StandarError;

import feign.FeignException;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(FeignException.class)
	public ResponseEntity<StandarError>objectNotFound(FeignException e, HttpServletRequest request){
		StandarError err = new StandarError(HttpStatus.NOT_FOUND.value(),e.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}
	

}
