package com.inacioalves.microservice.user_plant.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.inacioalves.microservice.user_plant.dto.UserDto;
import com.inacioalves.microservice.user_plant.model.User;

@Mapper
public interface UserMapper {

	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
	
	User toModel(UserDto userDto);
	UserDto tpDto(User user);
}
