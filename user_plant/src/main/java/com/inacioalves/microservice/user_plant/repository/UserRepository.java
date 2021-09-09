package com.inacioalves.microservice.user_plant.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inacioalves.microservice.user_plant.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
