package com.americanas.starwars;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

@SpringBootApplication
@EnableAutoConfiguration(exclude={MongoAutoConfiguration.class})
public class AmericanasStarwarsApplication {

	public static void main(String[] args) {
		SpringApplication springApplication = new SpringApplication(AmericanasStarwarsApplication.class);
		springApplication.setWebApplicationType(WebApplicationType.REACTIVE);
		springApplication.run(args);
	}
}