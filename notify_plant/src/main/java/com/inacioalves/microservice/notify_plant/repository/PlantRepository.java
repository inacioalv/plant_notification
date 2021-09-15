package com.inacioalves.microservice.notify_plant.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.inacioalves.microservice.notify_plant.model.Plant;



public interface PlantRepository extends JpaRepository<Plant, Long> {

}
