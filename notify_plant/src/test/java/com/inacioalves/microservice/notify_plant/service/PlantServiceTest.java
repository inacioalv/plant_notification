package com.inacioalves.microservice.notify_plant.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.inacioalves.microservice.notify_plant.model.Plant;
import com.inacioalves.microservice.notify_plant.repository.PlantRepository;
import com.inacioalves.microservice.notify_plant.repository.PlantRepositoryTest;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class PlantServiceTest {
	
	@SpyBean
	PlantService plantService;
	
	@MockBean
	PlantRepository plantRepository;
	
	@Test
	public void deveAtualizarUmaPlanta() {
		Plant plant = PlantRepositoryTest.createPlant();
		plant.setId(1L);
		plant.setEmailFrom("email@gmail.com");
		
		when(plantRepository.save(plant)).thenReturn(plant);
		
		plantService.updateById(plant);
		
		verify(plantRepository, times(1)).save(plant);
		
	}
	
	

	
	

}
