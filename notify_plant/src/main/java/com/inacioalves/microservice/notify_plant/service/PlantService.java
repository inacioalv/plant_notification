package com.inacioalves.microservice.notify_plant.service;



import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.inacioalves.microservice.notify_plant.dto.MessageResponseDto;
import com.inacioalves.microservice.notify_plant.dto.PlantDto;
import com.inacioalves.microservice.notify_plant.exeption.objectNotFoundException;
import com.inacioalves.microservice.notify_plant.mapper.PlantMapper;
import com.inacioalves.microservice.notify_plant.model.Plant;
import com.inacioalves.microservice.notify_plant.repository.PlantRepository;

@Service
public class PlantService {
	
	private PlantRepository repository;
	
	private final PlantMapper plantMapper = PlantMapper.INSTANCE;

	public PlantService(PlantRepository repository) {
		super();
		this.repository = repository;
	}

	public MessageResponseDto createplant(PlantDto plantDto) {
		Plant plantSave = plantMapper.toModel(plantDto);

		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		Date date = new Date();
		plantDto.setWater(date);
		System.out.println(dateFormat.format(plantDto.getWater()));
		
		Plant savedplant = repository.save(plantSave);
		return createMessageResponse(savedplant.getId(), "Created plant with Id:");
	}
	
	public List<PlantDto> listAll(){
		List<Plant> allplant = repository.findAll();
		
		return allplant.stream()
				.map(plantMapper::tpDto)
				.collect(Collectors.toList());
	}
	
	public PlantDto findById(Long id) throws objectNotFoundException {
		Plant plant =verifyIfExists(id);
		
		return plantMapper.tpDto(plant);
	}
	
//	public PlantDto findByUse(String name,String emailFrom) {
//		
//		return plantMapper.tpDto(name,emailFrom);
//	}
//	
//	public List<Plant> findByPlant(Long id) throws objectNotFoundException{
//		plant plant =verifyIfExists(id);
//		
//		return plant.getPlant();
//		
//	}
	


	public void deleteById(Long id) throws objectNotFoundException {
		verifyIfExists(id);
		
		repository.deleteById(id);
	}
	

	public Plant updateById(Plant plant)  {
		return  repository.save(plant);
		
	}
	
	
	private Plant verifyIfExists(Long id) throws objectNotFoundException {
		return repository.findById(id)
				.orElseThrow(() -> new objectNotFoundException("Plant not found with ID:"+id));
	}
	
	
	private MessageResponseDto createMessageResponse(Long id,String message) {
		return MessageResponseDto
				.builder()
				.message(message+id)
				.build();
	}



}
