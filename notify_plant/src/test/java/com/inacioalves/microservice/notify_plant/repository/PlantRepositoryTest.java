package com.inacioalves.microservice.notify_plant.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.inacioalves.microservice.notify_plant.model.Plant;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles("test")
public class PlantRepositoryTest {
	
	@Autowired
	PlantRepository plantRepository;
	
	@Autowired
	TestEntityManager entityManager;
	
	@Test
	public void must_persist_Plant_in_the_database() {
		Plant plant = createPlant();
		plant = plantRepository.save(plant);
		
		assertThat(plant.getId()).isNotNull();
	}
	
	@Test
	public void must_check_the_existence_Plant() {
		Plant plant = createPlant();
		entityManager.persist(plant);
		
		Optional<Plant> plantfound = plantRepository.findById(plant.getId());
		
		assertThat(plantfound.isPresent()).isTrue();
	
	}
	
	@Test
	public void return_When_is_no_plant_registered_withId() {
		Optional<Plant> plantfound = plantRepository.findById(1L);
		
		assertThat(plantfound.isPresent()).isFalse();
	}
	
	
	@Test
	public void returnPlantFindById() {
		Plant plant = createPersistirThePlant();
		
		Optional<Plant> plantfound = plantRepository.findById(plant.getId());
		
		assertThat(plantfound.isPresent()).isTrue();
	}
	
	@Test
	public void returnDeletePlant() {
		Plant plant = createPersistirThePlant();
		
		plant = entityManager.find(Plant.class, plant.getId());
		
		plantRepository.delete(plant);
		
		Plant plantNonexistent = entityManager.find(Plant.class, plant.getId());
		assertThat(plantNonexistent).isNull();
	}
	
	@Test
	public void returnUpdattePlant() {
		Plant plant = createPersistirThePlant();
		
		plant.setName("name");
		plant.setEmailTo("email@gmail.com");
		plant.setTypePlants("plant");
		
		plantRepository.save(plant);
		
		Plant plantUpdated = entityManager.find(Plant.class, plant.getId());
		
		assertThat(plantUpdated.getName()).isEqualTo("name");
		assertThat(plantUpdated.getEmailTo()).isEqualTo("email@gmail.com");
		assertThat(plantUpdated.getTypePlants()).isEqualTo("plant");
		
		
	}
	

	
	private Plant createPersistirThePlant() {
		Plant plant = createPlant();
		entityManager.persist(plant);
		return plant;
	}
	
	public static Plant createPlant() {
		return Plant.builder()
						.name("nome")
						.emailTo("email@gmail.com")
						.typePlants("plant")
						.water("14:53")
						.build();
	}

}
