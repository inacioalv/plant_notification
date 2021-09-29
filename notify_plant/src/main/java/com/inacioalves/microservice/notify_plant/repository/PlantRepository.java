package com.inacioalves.microservice.notify_plant.repository;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inacioalves.microservice.notify_plant.model.Plant;



public interface PlantRepository extends JpaRepository<Plant, Long> {
	
	Optional<Plant> findByName(String name);

}
