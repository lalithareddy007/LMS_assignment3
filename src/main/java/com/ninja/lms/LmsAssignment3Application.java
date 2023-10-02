package com.ninja.lms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan(basePackages = "com.ninja.lms")
public class LmsAssignment3Application {

	public static void main(String[] args) {
		SpringApplication.run(LmsAssignment3Application.class, args);
	}

}
