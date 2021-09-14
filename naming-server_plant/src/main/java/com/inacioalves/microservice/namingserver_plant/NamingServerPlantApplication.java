package com.inacioalves.microservice.namingserver_plant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class NamingServerPlantApplication {

	public static void main(String[] args) {
		SpringApplication.run(NamingServerPlantApplication.class, args);
	}

}
