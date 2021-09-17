package com.inacioalves.microservice.notifications.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inacioalves.microservice.notifications.model.EmailModel;


public interface EmailRepository extends JpaRepository<EmailModel, UUID> {

}
