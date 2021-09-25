package com.inacioalves.microservice.notify_plant.controller;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.inacioalves.microservice.notify_plant.exeption.objectNotFoundException;
import com.inacioalves.microservice.notify_plant.model.Plant;
import com.inacioalves.microservice.notify_plant.service.PlantService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping("/plant")
@Api(value = "Api Rest plant")
@CrossOrigin(origins = "*")
public class PlantController {
	
	private PlantService plantService;
	
	private Logger logger = LoggerFactory.getLogger(PlantController.class);
	
	public PlantController(PlantService plantService) {
		super();
		this.plantService = plantService;
	}

	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	@ApiOperation(value = "Create plant")
	public MessageResponseDto createplant(@RequestBody PlantDto PlantDto) throws objectNotFoundException {
		return plantService.createplant(PlantDto);
	}
	
	@GetMapping("/all")
	@ApiOperation(value = "Return list plant")
	public List<PlantDto> listAll(){
		logger.info("retrieveExchangeValue called with");
		return plantService.listAll();
	}
	
	@GetMapping("/{id}")
	@ApiOperation(value = "Return plant by Id")
	public PlantDto findById(@PathVariable Long id) throws objectNotFoundException {
		logger.info("retrieveExchangeValue called with {}",id);
		 return plantService.findById(id);
	 }
	
	 
	 @PutMapping("update/{id}")
	 @ApiOperation(value = "Update plant")
	 public ResponseEntity<Void> Updateplant(@RequestBody Plant plant,@PathVariable Long id){
		 	plant.setId(id);
			plantService.updateById(plant);
			return ResponseEntity.noContent().build();
		}
	 
	 @DeleteMapping("/{id}")
	 @ApiOperation(value = "Delete plant")
	 @ResponseStatus(code = HttpStatus.NO_CONTENT)
	 public void deleteById(@PathVariable  Long id) throws objectNotFoundException {
		 plantService.deleteById(id);
		 
	 }
	 
	 
//		@GetMapping("/name/{name}/emailFrom/{emailFrom}")
//		@ApiOperation(value = "Return plant by plant")
//		public PlantDto findByplant(@PathVariable String name,@PathVariable String emailFrom) throws objectNotFoundException {
//			 return plantService.findByUse(name,emailFrom);
//		 }
		
		
		
//		@GetMapping("/{id}/plant")
//		@ApiOperation(value = "Return plant by Id")
//		public List<Plant> findByPlant(@PathVariable Long id) throws objectNotFoundException {
//			return plantService.findByPlant(id);
//		}
	 
	

}
