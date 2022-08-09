package com.dave.questionservice;

import com.dave.questionservice.repositories.CustomRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = CustomRepositoryImpl.class)
public class QuestionServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuestionServiceApplication.class, args);
	}

}
