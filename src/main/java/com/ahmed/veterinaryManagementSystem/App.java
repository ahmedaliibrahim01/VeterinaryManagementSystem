package com.ahmed.veterinaryManagementSystem;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Veterinary Management System", version = "1.0", description = "V"))
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

}
