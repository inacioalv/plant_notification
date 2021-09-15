package com.inacioalves.microservice.notifications.config;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.inacioalves.microservice.notifications.model.Notify;


@FeignClient(name="plant",url="localhost:8000")
public interface NotifExchangeProxy {
	
//	@GetMapping("/user/name/{name}/emailFrom/{emailFrom}")
	@GetMapping("/plant/{id}")
	public Notify retrieveExchangeValue(
			@PathVariable Long id
//			@PathVariable String emailFrom
			);
	

}
