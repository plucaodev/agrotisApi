package com.agrotis.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.agrotis.api.domain")
@EnableJpaRepositories(basePackages = {"com.agrotis.api.domain.laboratorio", "com.agrotis.api.domain.propriedade", "com.agrotis.api.domain.usuario"})
public class ApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

}
