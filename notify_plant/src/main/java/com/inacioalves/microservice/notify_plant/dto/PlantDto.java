package com.inacioalves.microservice.notify_plant.dto;



import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

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
	@NotBlank(message = "Nome é obrigatório")
	@Length(max = 80, min = 10)
	private String name;
	@NotBlank(message = "Email é obrigatório")
	@Email(message = "Informe um email correto")
	private String emailFrom;
    @NotBlank(message = "Tipo da planta é obrigatório")
	private String typePlants;
    @NotBlank(message = "Tipo da water é obrigatório")
    @Length(max = 5, min = 4)
	private String  water;

	
	
	

}
