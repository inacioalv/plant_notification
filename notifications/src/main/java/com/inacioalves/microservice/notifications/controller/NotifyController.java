package com.inacioalves.microservice.notifications.controller;




import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.inacioalves.microservice.notifications.config.NotifExchangeProxy;
import com.inacioalves.microservice.notifications.model.Notify;


@RestController
public class NotifyController {
	
	
	private NotifExchangeProxy notifExchangeProxy;

	public NotifyController(NotifExchangeProxy notifExchangeProxy) {
		super();
		this.notifExchangeProxy = notifExchangeProxy;
	}


	@GetMapping("/plant-conversion/id/{id}")
	public Notify getPlant(@PathVariable Long id) {
		
		
		Notify notifyConversion = notifExchangeProxy.retrieveExchangeValue(id);
		
		
		return new Notify(
				notifyConversion.getId(),
				notifyConversion.getName(),
				notifyConversion.getEmailFrom(),
				notifyConversion.getTypePlants(),
				notifyConversion.getWater()
				);
	}
	
	
	

}
