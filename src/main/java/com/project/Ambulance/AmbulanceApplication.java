package com.project.Ambulance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class AmbulanceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AmbulanceApplication.class, args);
	}

}
