package com.inacioalves.microservice.notifications.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Notify {
	
	private Long id;
	private String name;
	private String emailTo;
	private String typePlants;
	private String time_to_water;
	

}
