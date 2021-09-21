package com.inacioalves.microservice.notify_plant.controller;

import static org.mockito.Mockito.when;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.*;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import com.inacioalves.microservice.notify_plant.dto.PlantDto;
import com.inacioalves.microservice.notify_plant.exeption.objectNotFoundException;
import com.inacioalves.microservice.notify_plant.service.PlantService;

import io.restassured.http.ContentType;

@WebMvcTest
public class PlantControllerTest {
	
	@Autowired
	private PlantController controller;
	
	@MockBean
	private PlantService plantService;
	
	@BeforeEach
	public void setup() {
		standaloneSetup(controller);
	}
	
	@Test
	public void deveRetornaSucesso_QuandoBuscarPlant() throws objectNotFoundException {
		
		when(this.plantService.findById(1L))
			.thenReturn(new PlantDto(1L,"inacio","juninhomend@gmail.com","plant",new Date()));
	
		given()
				.accept(ContentType.JSON)
		.when()
				.get("/plant/{id}",1L)
		.then()
				.statusCode(HttpStatus.OK.value());
	}

}
