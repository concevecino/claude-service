package com.ejemplo.claude;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class ClaudeServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClaudeServiceApplication.class, args);
    }
}
