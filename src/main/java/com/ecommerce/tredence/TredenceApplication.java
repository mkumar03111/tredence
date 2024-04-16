package com.ecommerce.tredence;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.ecommerce.tredence.api.repository"})
public class TredenceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TredenceApplication.class, args);
	}

}
