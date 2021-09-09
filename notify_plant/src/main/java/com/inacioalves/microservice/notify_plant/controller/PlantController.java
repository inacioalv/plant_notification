package com.inacioalves.microservice.notify_plant.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.inacioalves.microservice.notify_plant.dto.MessageResponseDto;
import com.inacioalves.microservice.notify_plant.dto.PlantDto;
import com.inacioalves.microservice.notify_plant.exeption.PlantNotFoundExeption;
import com.inacioalves.microservice.notify_plant.model.Plant;
import com.inacioalves.microservice.notify_plant.service.PlantService;

@RestController
@RequestMapping("/plant")
public class PlantController {
	
	private final PlantService plantService;

	public PlantController(PlantService plantService) {
		super();
		this.plantService = plantService;
	}
	
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public MessageResponseDto createPlant(@RequestBody PlantDto plantDto) {
		return plantService.createPlant(plantDto);
	}
	
	@GetMapping("/all")
	public List<PlantDto> listAll(){
		return plantService.listAll();
	}
	
	@GetMapping("/{id}")
	 public PlantDto findById(@PathVariable Long id) throws PlantNotFoundExeption {
		 return plantService.findById(id);
	 }
	 
	 @PutMapping("update/{id}")
	 public ResponseEntity<Void> UpdateUser(@RequestBody Plant plant,@PathVariable Long id){
		 	plant.setId(id);
			plantService.updateById(plant);
			return ResponseEntity.noContent().build();
		}
	 
	 @DeleteMapping("/{id}")
	 @ResponseStatus(code = HttpStatus.NO_CONTENT)
	 public void deleteById(@PathVariable  Long id) throws PlantNotFoundExeption {
		 plantService.deleteById(id);
		 
	 }

}
