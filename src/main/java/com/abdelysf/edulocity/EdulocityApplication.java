package com.abdelysf.edulocity;

import com.abdelysf.edulocity.config.SwaggerConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
@Import(SwaggerConfiguration.class) // import the configuration of swagger
public class EdulocityApplication {

	public static void main(String[] args) {
		SpringApplication.run(EdulocityApplication.class, args);
	}


}
