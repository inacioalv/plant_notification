package com.inacioalves.microservice.notify_plant.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.inacioalves.microservice.notify_plant.model.User;


public interface UserRepository extends JpaRepository<User, Long> {

}
