package com.inacioalves.microservice.notify_plant.controller;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.standaloneSetup;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

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
	
	@Autowired
	MockMvc mvc;
	
	
	@BeforeEach
	public void setup() {
		standaloneSetup(controller);
	}
	
	@Test
	public void shouldreturnsuccess_when_searchingListplant() throws objectNotFoundException {
		
		when(this.plantService.listAll())
			.thenReturn(new ArrayList<PlantDto>());
	
		given()
				.accept(ContentType.JSON)
		.when()
				.get("/plant/all")
		.then()
				.statusCode(HttpStatus.OK.value());
	}
	
	
	@Test
	public void return_success_when_searching_plantById() throws objectNotFoundException {

		when(this.plantService.findById(1L))
		.thenReturn(extracted());
	
		given()
				.accept(ContentType.JSON)
		.when()
				.get("/plant/{id}",1L)
		.then()
				.statusCode(HttpStatus.OK.value());
	}

	static final MediaType JSON = MediaType.APPLICATION_JSON;


	private PlantDto extracted() {
		return new PlantDto(1L,"nome","email@gmail.com","plant",new Date());
	}
	
	
	
	
	
	
	

}
