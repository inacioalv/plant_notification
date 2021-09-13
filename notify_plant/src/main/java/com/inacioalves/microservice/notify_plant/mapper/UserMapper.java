package com.inacioalves.microservice.notify_plant.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.inacioalves.microservice.notify_plant.dto.UserDto;
import com.inacioalves.microservice.notify_plant.model.User;


@Mapper
public interface UserMapper {

	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
	
	User toModel(UserDto userDto);
	UserDto tpDto(User user);
}
