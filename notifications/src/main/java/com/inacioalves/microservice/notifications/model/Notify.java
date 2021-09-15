package com.inacioalves.microservice.notifications.model;

import java.time.LocalDate;
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
	private String emailFrom;
	private String typePlants;
	private LocalDate water;
	

}
