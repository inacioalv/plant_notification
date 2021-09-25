package com.inacioalves.microservice.notify_plant.dto;


import java.time.LocalDateTime;
import java.util.Date;

//import java.util.List;

//import com.inacioalves.microservice.notify_plant.model.Plant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PlantDto {
	
	private Long id;
	private String name;
	private String emailFrom;
	private String typePlants;
	private Date  water;

	
	
	

}
