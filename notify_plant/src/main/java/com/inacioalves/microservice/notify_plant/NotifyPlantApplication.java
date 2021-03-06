package com.inacioalves.microservice.notify_plant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients("com.inacioalves.microservice.notify_plant")
public class NotifyPlantApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotifyPlantApplication.class, args);
	}

}
