package com.inacioalves.microservice.notifications.controller;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.inacioalves.microservice.notifications.dto.EmailDto;
import com.inacioalves.microservice.notifications.model.EmailModel;
import com.inacioalves.microservice.notifications.service.EmailService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;



@RestController
@Api(value = "Api Rest Email")
@CrossOrigin(origins = "*")
public class EmailController {
	
	@Autowired
	EmailService emailService;
	
	@PostMapping(value = "/sending-email/id/{id}")
	@ApiOperation(value = "Post email")
	public ResponseEntity<EmailModel> sendingEmail(@RequestBody @Valid EmailDto emailDto,@PathVariable Long id ){
		EmailModel emailModel = new EmailModel();
		BeanUtils.copyProperties(emailDto, emailModel);
		emailService.sendEmail(emailModel,id);
		return new ResponseEntity<>(emailModel,HttpStatus.CREATED);
	}

}
