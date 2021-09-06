package com.americanas.starwars.api.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class WebClientStarWars {

	@Value("${app.swapi.rootPath}")
	private String swapiPath;
	
	@Bean
	public WebClient webClientPatient(WebClient.Builder builder) {
		return builder
			.baseUrl(swapiPath)
			.build();
	}
}