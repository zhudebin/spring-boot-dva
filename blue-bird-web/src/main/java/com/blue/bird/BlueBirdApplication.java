package com.blue.bird;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.blue.bird"})
public class BlueBirdApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlueBirdApplication.class, args);
	}
}
