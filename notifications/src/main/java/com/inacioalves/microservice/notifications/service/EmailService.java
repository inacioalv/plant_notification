package com.inacioalves.microservice.notifications.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.inacioalves.microservice.notifications.config.NotifExchangeProxy;
import com.inacioalves.microservice.notifications.enums.StatusEmail;
import com.inacioalves.microservice.notifications.model.EmailModel;
import com.inacioalves.microservice.notifications.model.Notify;
import com.inacioalves.microservice.notifications.repository.EmailRepository;

@Service
public class EmailService {
	
	@Autowired
	EmailRepository emailRepository;
	
	@Autowired
	private JavaMailSender emailSender;
	
	@Autowired
	private NotifExchangeProxy notifExchangeProxy;

	public EmailModel sendEmail(EmailModel emailModel, Long id) {
		emailModel.setSendDateEmail(LocalDateTime.now());
		
		Notify notifyConversion = notifExchangeProxy.retrieveExchangeValue(id);
		emailModel.setEmailFrom(notifyConversion.getEmailFrom());
		
		
		try {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setFrom(emailModel.getEmailFrom());
			message.setTo(emailModel.getEmailTo());
			message.setSubject(emailModel.getSubject());
			message.setText(emailModel.getText());
			emailSender.send(message);
			
			emailModel.setStatusEmail(StatusEmail.SENT);
		} catch (MailException e) {
			emailModel.setStatusEmail(StatusEmail.ERROR);
		}finally{
			return emailRepository.save(emailModel);
		}
		
	}

}