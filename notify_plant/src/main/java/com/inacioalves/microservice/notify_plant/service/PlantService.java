package com.inacioalves.microservice.notify_plant.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.inacioalves.microservice.notify_plant.dto.MessageResponseDto;
import com.inacioalves.microservice.notify_plant.dto.PlantDto;
import com.inacioalves.microservice.notify_plant.exeption.PlantNotFoundExeption;
import com.inacioalves.microservice.notify_plant.maper.PlantMapper;
import com.inacioalves.microservice.notify_plant.model.Plant;
import com.inacioalves.microservice.notify_plant.repository.PlantRepository;


@Service
public class PlantService {
	
	private PlantRepository plantRepository;
	
	private final PlantMapper plantMapper = PlantMapper.INSTANCE;
	
	public PlantService(PlantRepository plantRepository) {
		super();
		this.plantRepository = plantRepository;
	}


	public MessageResponseDto createPlant(PlantDto plantDto) {
		Plant plantSave = plantMapper.toModel(plantDto);
		
		Plant savedPlant = plantRepository.save(plantSave);
		return createMessageResponse(savedPlant.getId(), "Created plant with Id:");
	}
	
	public List<PlantDto> listAll(){
		List<Plant> allPlant = plantRepository.findAll();
		
		return allPlant.stream()
				.map(plantMapper::tpDto)
				.collect(Collectors.toList());
	}
	
	public PlantDto findById(Long id) throws PlantNotFoundExeption {
		Plant plant =verifyIfExists(id);
		
		return plantMapper.tpDto(plant);
	}
	


	public void deleteById(Long id) throws PlantNotFoundExeption {
		verifyIfExists(id);
		
		plantRepository.deleteById(id);
	}
	

	public Plant updateById(Plant plant)  {
		return  plantRepository.save(plant);
		
	}
	
	
	private Plant verifyIfExists(Long id) throws PlantNotFoundExeption {
		return plantRepository.findById(id)
				.orElseThrow(() -> new PlantNotFoundExeption(id));
	}
	
	
	private MessageResponseDto createMessageResponse(Long id,String message) {
		return MessageResponseDto
				.builder()
				.message(message+id)
				.build();
	}
	
	

}
