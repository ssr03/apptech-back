package com.platform.apptechback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan
@SpringBootApplication
public class ApptechBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApptechBackApplication.class, args);
	}

}
