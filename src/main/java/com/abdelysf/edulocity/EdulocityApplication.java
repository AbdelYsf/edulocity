package com.abdelysf.edulocity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class EdulocityApplication {

	public static void main(String[] args) {
		SpringApplication.run(EdulocityApplication.class, args);
	}


}
