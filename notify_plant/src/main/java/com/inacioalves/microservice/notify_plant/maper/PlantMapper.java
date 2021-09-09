package com.inacioalves.microservice.notify_plant.maper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.inacioalves.microservice.notify_plant.dto.PlantDto;
import com.inacioalves.microservice.notify_plant.model.Plant;

@Mapper
public interface PlantMapper {

	PlantMapper INSTANCE = Mappers.getMapper(PlantMapper.class);
	
	Plant toModel(PlantDto plantDto);
	PlantDto tpDto(Plant plant);
	
}
