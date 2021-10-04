package com.inacioalves.microservice.notifications.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.inacioalves.microservice.notifications.enums.StatusEmail;
import com.inacioalves.microservice.notifications.model.EmailModel;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles("test")
public class EmailRepositoryTest {
	
	@Autowired
	EmailRepository emailRepository;
	
	@Autowired
	TestEntityManager entityManager;
	
	@Test
	public void must_persist_EmailModel_in_the_database() {
		EmailModel emailModel = createEmail();
		
		emailRepository.save(emailModel);
		
		assertThat(emailModel.getEmailId()).isNotNull();
		
	}
	
	
	public static EmailModel createEmail() {
		return EmailModel.builder()
				.ownerRef("name user")
				.emailFrom("email@gmail.com")
				.emailTo("email@gmail.com")
				.subject("plant subject")
				.text("plant")
				.sendDateEmail(LocalDateTime.now())
				.statusEmail(StatusEmail.SENT)
				.build();
	}
	

}
