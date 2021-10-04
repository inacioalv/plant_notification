package com.inacioalves.microservice.notify_plant.controller;

import static com.inacioalves.microservice.notify_plant.utils.JsonConvertionUtils.asJsonString;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;


import com.inacioalves.microservice.notify_plant.dto.PlantDto;
import com.inacioalves.microservice.notify_plant.service.PlantService;
import com.inacioalves.microservice.notify_plant.service.PlantServiceTest;



@ExtendWith(MockitoExtension.class)
public class PlantControllerTest {
	
	private static String API_URL_PATH="/plant";

	private MockMvc mockMvc;
	
	@Mock
	private PlantService plantService;
	
	@InjectMocks
	private PlantController controller;
	
	
	
	@BeforeEach
	public void setup() {
		mockMvc= MockMvcBuilders.standaloneSetup(controller)
				.setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
				.setViewResolvers((s,locale) -> new MappingJackson2JsonView())
				.build();
	}
	
	@Test
	public void whenPOSTIsCalledThenAPlantIsCreated() throws Exception {
		PlantDto plantDto = PlantServiceTest.createPlant();
		
		//when
		when(plantService.createPlant(plantDto)).thenReturn(plantDto);
		
		//then
		mockMvc.perform(post(API_URL_PATH)
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(plantDto)))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.name",is(plantDto.getName())))
				.andExpect(jsonPath("$.emailTo",is(plantDto.getEmailTo())))
				.andExpect(jsonPath("$.typePlants",is(plantDto.getTypePlants())))
				.andExpect(jsonPath("$.time_to_water",is(plantDto.getTime_to_water())));
		
	}
	

	@Test
	public void WhenToCreateToPlantThisIsCalledValidationNameThenHeMustReturnBadRequestedStatus() throws Exception {
		PlantDto plantDto = PlantServiceTest.createPlant();
		plantDto.setName(null);
		
		//the
		mockMvc.perform(post(API_URL_PATH)
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(plantDto)))
				.andExpect(status().isBadRequest());
			
	}
	
	@Test
	public void WhenToCreateToPlantThisIsCalledValidationEmailThenHeMustReturnBadRequestedStatus() throws Exception {
		PlantDto plantDto = PlantServiceTest.createPlant();
		plantDto.setEmailTo(null);
		
		//the
		mockMvc.perform(post(API_URL_PATH)
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(plantDto)))
				.andExpect(status().isBadRequest());
	}
	
	@Test
	public void WhenToCreateToPlantThisIsCalledValidationEmailNotValidThenHeMustReturnBadRequestedStatus() throws Exception {
		PlantDto plantDto = PlantServiceTest.createPlant();
		plantDto.setEmailTo("Email invalid");
		
		//the
		mockMvc.perform(post(API_URL_PATH)
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(plantDto)))
				.andExpect(status().isBadRequest());
	}
	
	@Test
	public void WhenToCreateToPlantThisIsCalledValidationTypePlantsThenHeMustReturnBadRequestedStatus() throws Exception {
		PlantDto plantDto = PlantServiceTest.createPlant();
		plantDto.setTypePlants(null);
		
		//the
		mockMvc.perform(post(API_URL_PATH)
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(plantDto)))
				.andExpect(status().isBadRequest());
	}
	
	@Test
	public void WhenToCreateToPlantThisIsCalledValidationWaterThenHeMustReturnBadRequestedStatus() throws Exception {
		PlantDto plantDto = PlantServiceTest.createPlant();
		plantDto.setTime_to_water(null);
		
		//the
		mockMvc.perform(post(API_URL_PATH)
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(plantDto)))
				.andExpect(status().isBadRequest());
	}
	
	@Test 
	public void WaterTobeBiggerWhat5ThenHeHemustReturnBadRequestedStatus() throws Exception {
		PlantDto plantDto = PlantServiceTest.createPlant();
		plantDto.setTime_to_water("10:500");
		
		//the
		mockMvc.perform(post(API_URL_PATH)
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(plantDto)))
				.andExpect(status().isBadRequest());
	}
	
	@Test 
	public void WaterTobeSmallerWhat4ThenHeHemustReturnBadRequestedStatus() throws Exception {
		PlantDto plantDto = PlantServiceTest.createPlant();
		plantDto.setTime_to_water("4");
		
		//the
		mockMvc.perform(post(API_URL_PATH)
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(plantDto)))
				.andExpect(status().isBadRequest());
	}
	
	@Test
	public void  whenGETIsCalledWithValid_Id_ThenOkStatusIsReturned() throws Exception {
		PlantDto plantDto = PlantServiceTest.createPlant();
		
		//when
		when(plantService.findById(plantDto.getId())).thenReturn(plantDto);
		
		//then
		mockMvc.perform(MockMvcRequestBuilders.get(API_URL_PATH+"/"+plantDto.getId())
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name", is(plantDto.getName())))
				.andExpect(jsonPath("$.emailTo", is(plantDto.getEmailTo())));
	}
	
	@Test
	public void whenGETListWithPlantIsCalledThenOkStatusIsReturned() throws Exception {
		//given
		PlantDto plantDto = PlantServiceTest.createPlant();
		
		//then
		when(plantService.listAll()).thenReturn(Collections.singletonList(plantDto));
		
		mockMvc.perform(MockMvcRequestBuilders.get(API_URL_PATH+"/all")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].name", is(plantDto.getName())))
				.andExpect(jsonPath("$[0].emailTo", is(plantDto.getEmailTo())));
	}
	
	@Test
	public void whenGETListWithoutPlantsIsCalledThenOkStatusIsReturned() throws Exception {
		//given
		PlantDto plantDto = PlantServiceTest.createPlant();
		
		//then
		when(plantService.listAll()).thenReturn(Collections.singletonList(plantDto));
		
		//then
		mockMvc.perform(MockMvcRequestBuilders.get(API_URL_PATH+"/all")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
	
	@Test
	public void whenDELETEIsCalledWithValidIdThenNoContentStatusIsReturned() throws Exception {
		//given
		PlantDto plantDto = PlantServiceTest.createPlant();
		
		//when
		doNothing().when(plantService).deleteById(plantDto.getId());
		
		//then
		mockMvc.perform(MockMvcRequestBuilders.delete(API_URL_PATH+"/"+plantDto.getId())
				.contentType(MediaType.APPLICATION_JSON))
        		.andExpect(status().isNoContent());
	}
	

}
