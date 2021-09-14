package com.inacioalves.microservice.notify_plant.dto;


import java.util.List;

import com.inacioalves.microservice.notify_plant.model.Plant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
	
	private Long id;
	private String name;
	private String emailFrom;
	private List<Plant> plant;

}
