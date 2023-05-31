package com.nhnacademy.springbootstudent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@ConfigurationPropertiesScan
public class SpringBootStudentApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootStudentApplication.class, args);
	}


}
