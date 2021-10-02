package com.inacioalves.microservice.notifications.controller;




import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.inacioalves.microservice.notifications.config.NotifExchangeProxy;
import com.inacioalves.microservice.notifications.model.Notify;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@RestController
@Api(value = "Api Rest  notify")
@CrossOrigin(origins = "*")
public class NotifyController {
	
	
	private NotifExchangeProxy notifExchangeProxy;

	public NotifyController(NotifExchangeProxy notifExchangeProxy) {
		super();
		this.notifExchangeProxy = notifExchangeProxy;
	}


	@GetMapping("/plant-conversion/id/{id}")
	@ApiOperation(value = "fetch the plant")
	public Notify getPlant(@PathVariable Long id) {
		
		
		Notify notifyConversion = notifExchangeProxy.retrieveExchangeValue(id);
		
		
		return new Notify(
				notifyConversion.getId(),
				notifyConversion.getName(),
				notifyConversion.getEmailTo(),
				notifyConversion.getTypePlants(),
				notifyConversion.getTime_to_water()
				);
	}
	
	
	

}
