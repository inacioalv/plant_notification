package com.inacioalves.microservice.notify_plant.dto;


import java.time.LocalDate;

import com.inacioalves.microservice.notify_plant.model.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlantDto {
	
	private Long id;
	private String typePlants;
	private LocalDate water;
	private User user;

}
