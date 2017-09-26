package com.blue.bird;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class BlueBirdApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlueBirdApplication.class, args);
    }
}
