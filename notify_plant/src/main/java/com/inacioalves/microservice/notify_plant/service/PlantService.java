package com.inacioalves.microservice.notify_plant.service;




import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

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


	public PlantDto createPlant(PlantDto plantDto)throws objectNotFoundException  {
		verifyIfIsAlreadyRegistered(plantDto.getName());
		Plant plantSave = plantMapper.toModel(plantDto);
		Plant savePlant= repository.save(plantSave);
		return plantMapper.tpDto(savePlant);
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
	

	public void deleteById(Long id) throws objectNotFoundException {
		verifyIfExists(id);
		
		repository.deleteById(id);
	}
	

	public PlantDto updateById(PlantDto plantDto,Long id)  {
		verifyIfExists(id);
		Plant plantSave = plantMapper.toModel(plantDto);
		Plant savePlant= repository.save(plantSave);
		return  plantMapper.tpDto(savePlant);
		
	}
	
	
	private Plant verifyIfExists(Long id) throws objectNotFoundException {
		return repository.findById(id)
				.orElseThrow(() -> new objectNotFoundException("Plant not found with ID:"+id));
	}
	
	private void verifyIfIsAlreadyRegistered(String name) throws objectNotFoundException {
        Optional<Plant> optSavedBeer = repository.findByName(name);
        if (optSavedBeer.isPresent()) {
            throw new objectNotFoundException("Plant with already existing name:"+name);
        }
    }
	
	
//	public PlantDto findByUse(String name,String emailFrom) {
//	
//	return plantMapper.tpDto(name,emailFrom);
//}
//
//public List<Plant> findByPlant(Long id) throws objectNotFoundException{
//	plant plant =verifyIfExists(id);
//	
//	return plant.getPlant();
//	
//}




}
