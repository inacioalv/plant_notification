package com.inacioalves.microservice.notify_plant.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.inacioalves.microservice.notify_plant.dto.PlantDto;
import com.inacioalves.microservice.notify_plant.exeption.objectNotFoundException;
import com.inacioalves.microservice.notify_plant.mapper.PlantMapper;
import com.inacioalves.microservice.notify_plant.model.Plant;
import com.inacioalves.microservice.notify_plant.repository.PlantRepository;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class PlantServiceTest {
	
	@InjectMocks
	PlantService plantService;
	
	private final PlantMapper plantMapper = PlantMapper.INSTANCE;
	
	@Mock
	PlantRepository plantRepository;
	
	@Test
	public void whenPlantInformedThenItshouldBeCreated() {
		//given
		PlantDto expectedPlantDto = createPlant();
		Plant expectedSavedPlant = plantMapper.toModel(expectedPlantDto);
		
		//when
		when(plantRepository
				.findById(expectedPlantDto.getId()))
				.thenReturn(Optional.empty());
		
		when(plantRepository
				.save(expectedSavedPlant))
				.thenReturn(expectedSavedPlant);
		//then
		PlantDto createPlantDto = 
				plantService.createPlant(expectedPlantDto);
		
		
		assertThat(createPlantDto.getId(), 
				is(equalTo(createPlantDto.getId())));
		
		assertThat(createPlantDto.getName(), 
				is(equalTo(createPlantDto.getName())));
		
		assertThat(createPlantDto.getEmailTo(), 
				is(equalTo(createPlantDto.getEmailTo())));
		
	}
	
	@Test
	public void  whenAlreadyRegisteredPlantInformdThenAnExeptionShouldBeThrown() {
		//given
		PlantDto expectedPlantDto = createPlant();
		Plant duplicatedPlant = plantMapper.toModel(expectedPlantDto);
	
		//when
		when(plantRepository
					.findByName(expectedPlantDto.getName()))
					.thenReturn(Optional.of(duplicatedPlant));
		//then
		assertThrows(objectNotFoundException.class, 
				()-> plantService.createPlant(expectedPlantDto));
		
		
	}
	
	@Test
	public void whenValidPlantIsGivenThenReturnAPlantById() {
		//given
		PlantDto expectedFoundPlantDto = createPlant();
		Plant expectedFoundPlant = plantMapper.toModel(expectedFoundPlantDto);
		
		//when
		when(plantRepository
					.findById(expectedFoundPlant.getId()))
					.thenReturn(Optional.of(expectedFoundPlant));
		//then
		PlantDto foundPlantDto = 
					plantService.findById(expectedFoundPlantDto.getId());
		
		assertThat(foundPlantDto,
				is (equalTo(expectedFoundPlantDto)));
	}
	
	@Test
	public void whenNotRegisteredPlnatByIdIsGivenThenThrowAnExeption() {
		//given
		PlantDto expectedFoundPlantDto = createPlant();
		
		//when
		when(plantRepository
					.findById(expectedFoundPlantDto.getId()))
					.thenReturn(Optional.empty());
		//then
		assertThrows(objectNotFoundException.class, 
						()-> plantService.findById(expectedFoundPlantDto.getId()));
		
	}
	
	@Test
	public void whenListPlantIsCalledThenReturnAListOfPlants() {
		//given
		PlantDto expectedFoundPlantDto = createPlant();
		Plant expectedFoundPlant = plantMapper.toModel(expectedFoundPlantDto);
	
		//when
		when(plantRepository.findAll())
			.thenReturn(Collections.singletonList(expectedFoundPlant));
		
		//then
		List<PlantDto> foundListPlantDto = plantService.listAll();
		
		assertThat(foundListPlantDto, is(not(empty())));
		assertThat(foundListPlantDto.get(0), is(equalTo(expectedFoundPlantDto)));
		
	}
	
	@Test
	public void whenListPlantIsCalledThenReturnAnEmptyListOfPlants() {
		//when
		when(plantRepository.findAll()).thenReturn(Collections.emptyList());
		
		//then
		List<PlantDto> foundListPlantDto = plantService.listAll();
		
		assertThat(foundListPlantDto, is(empty()));
	}
	
	@Test
	public void  whenExclusionIsCalledWithValidIdThenAPlantShouldBeDeleted() {
		//given
		PlantDto expectedDeletedPlantDto = createPlant();
		Plant expectedDeletedPlant = plantMapper.toModel(expectedDeletedPlantDto);
	
		//when
		when(plantRepository
				   .findById(expectedDeletedPlantDto.getId()))
				   .thenReturn(Optional.of(expectedDeletedPlant));
		doNothing().when(plantRepository)
				   .deleteById(expectedDeletedPlantDto.getId());
		
		//then
		plantService.deleteById(expectedDeletedPlantDto.getId());
		
		verify(plantRepository,times(1))
				.findById(expectedDeletedPlantDto.getId());
		verify(plantRepository,times(1))
				.deleteById(expectedDeletedPlantDto.getId());
	}
	
	@Test
	public void WhenExclusionAndCalledWithmistakeattheIdentificationThenReturnsnotfound() {
		//given
		PlantDto expectedDeletedPlantDto = createPlant();
	
		//when
		when(plantRepository
				   .findById(expectedDeletedPlantDto.getId()))
				   .thenReturn(Optional.empty());
		
		//then
		assertThrows(objectNotFoundException.class, 
						()-> plantService.deleteById(expectedDeletedPlantDto.getId()));
	}
	
	@Test
	public void WhenupdateItsCalledwithValihemustthenupdateTheplant() {
		//given
		PlantDto expectedPlantDto = createPlant();
		expectedPlantDto.setId(1L);
		Plant expectedSavedPlant = plantMapper.toModel(expectedPlantDto);
				
		//when
		when(plantRepository
					.findById(expectedSavedPlant.getId()))
					.thenReturn(Optional.of(expectedSavedPlant));
				
		when(plantRepository
					.save(expectedSavedPlant))
					.thenReturn(expectedSavedPlant);
		//then
		PlantDto createPlantDto = 
						plantService.updateById(expectedPlantDto,1L);
				
				
				assertThat(createPlantDto.getId(), 
						is(equalTo(createPlantDto.getId())));
				
				assertThat(createPlantDto.getName(), 
						is(equalTo(createPlantDto.getName())));
				
				assertThat(createPlantDto.getEmailTo(), 
						is(equalTo(createPlantDto.getEmailTo())));
		
	}
	
	@Test
	public void WhenUpdateAndCalledWithmistakeattheIdentificationThenReturnsnotfound() {
		//given
		PlantDto expectedDeletedPlantDto = createPlant();
			
		//when
		when(plantRepository
					.findById(expectedDeletedPlantDto.getId()))
					.thenReturn(Optional.empty());
				
		//then
		assertThrows(objectNotFoundException.class, 
							()-> plantService.updateById(expectedDeletedPlantDto,expectedDeletedPlantDto.getId()));
		
	}
	
	
	
	
	public static PlantDto createPlant() {
		return PlantDto.builder()
						.name("nome")
						.emailTo("email@gmail.com")
						.typePlants("plant")
						.water("14:53")
						.build();
	}
	

	
	

}
