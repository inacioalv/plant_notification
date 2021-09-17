package com.inacioalves.microservice.notifications.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.inacioalves.microservice.notifications.dto.EmailDto;
import com.inacioalves.microservice.notifications.model.EmailModel;
import com.inacioalves.microservice.notifications.service.EmailService;



@Component
public class EmailConsumer {
	
	@Autowired
	EmailService emailService;
	
	@RabbitListener(queues ="${spring.rabbitmq.queue}")
	public void listen(@Payload EmailDto emailDto,Long id) {
		EmailModel emailModel =  new EmailModel();
		BeanUtils.copyProperties(emailDto, emailModel);
		emailService.sendEmail(emailModel,id);
		System.out.println("Email status:" + emailModel.getStatusEmail().toString());
	}

}

