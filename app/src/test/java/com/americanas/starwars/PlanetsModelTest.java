package com.americanas.starwars;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.americanas.starwars.api.controllers.PlanetsController;
import com.americanas.starwars.api.models.PlanetsModel;
import com.americanas.starwars.domain.services.PlanetsService;

import reactor.core.publisher.Mono;

@RunWith(SpringRunner.class)
@WebFluxTest(controllers = PlanetsController.class)
public class PlanetsModelTest {
	
	@Autowired 
	WebTestClient client;
	
	@MockBean 
	PlanetsService gateway;
	
	@Test
	public void testGetProductByCodeShouldBeOk() {
		
//		final PlanetsModel planet = new PlanetsModel("613346d5318644f2b6d51b19", 1, "Tatooine", "arid", "desert", 0);
//		
//		given(gateway.findById("613346d5318644f2b6d51b19")).willReturn(Mono.just(planet));
//		
//		client.get().uri("/products/BBB-1111")
//			.exchange()
//			.expectStatus().isOk()
//			.expectBody()
//			.jsonPath("$.id").isEqualTo("613346d5318644f2b6d51b19")
//			.jsonPath("$.swapi_id").isEqualTo(1)
//			.jsonPath("$.name").isEqualTo("Tatooine")
//			.jsonPath("$.climate").isEqualTo("arid")
//			.jsonPath("$.terrain").isEqualTo("desert")
//			.jsonPath("$.filmsCount").isEqualTo(0);
	}
}
