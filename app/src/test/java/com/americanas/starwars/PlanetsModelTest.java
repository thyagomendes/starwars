package com.americanas.starwars;

import static org.mockito.Mockito.times;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.americanas.starwars.api.controllers.PlanetsController;
import com.americanas.starwars.api.models.PlanetsModel;
import com.americanas.starwars.api.models.PlanetsResumeModel;
import com.americanas.starwars.domain.services.PlanetsService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Import(PlanetsService.class)
@RunWith(SpringRunner.class)
@WebFluxTest(controllers = PlanetsController.class )
public class PlanetsModelTest {
	
	@Autowired
	PlanetsService planetsService;
	
	@MockBean 
	PlanetsService repository;
	
	@Autowired
	WebTestClient client;
	
	@Value("${app.server.root}")
	String swapiPath;
	
	@Value("${app.swapi.planets}")
	private String URI;

	String id = "613346d5318644f2b6d51b19";

	PlanetsModel planet = new PlanetsModel(id, 1, "Tatooine", "arid", "desert", 0);
	PlanetsResumeModel planetRM = new PlanetsResumeModel(id, 1, "Tatooine", "arid", "desert");
	
	@Test
	public void testGetPlanetsFindByIdOk() {
		
        Mockito
            .when(repository.findById(id))
            .thenReturn(Mono.just(planet));

        client
        	.get().uri(swapiPath + URI + "/{id}", id)
            .exchange()
            .expectStatus().isOk()
            .expectBody()
            .jsonPath("$.id").isEqualTo(id)
            .jsonPath("$.name").isNotEmpty()
            .jsonPath("$.name").isEqualTo("Tatooine")
            .jsonPath("$.swapi_id").isEqualTo(1)
            .jsonPath("$.swapi_id").isNotEmpty()
            .jsonPath("$.climate").isEqualTo("arid")
            .jsonPath("$.climate").isNotEmpty()
            .jsonPath("$.terrain").isEqualTo("desert")
            .jsonPath("$.terrain").isNotEmpty();
         
        Mockito.verify(repository, times(1)).findById(id);		
	}
	
	@Test
	public void testGetPlanetsFindAllOk() {
		
        Mockito
            .when(repository.findAll())
            .thenReturn(Flux.just(planetRM));

        client
        	.get().uri(swapiPath + URI + "/")
            .exchange()
            .expectStatus().isOk()
            .expectBody();
         
        Mockito.verify(repository, times(1)).findAll();		
	}
	
	@Test
	public void testPostPlanetsCreateOk() {
		
		planetRM.setId(null);
		
        Mockito
            .when(repository.create(Mono.just(planetRM)))
            .thenReturn(Mono.just(planetRM));

        client
        	.post().uri(swapiPath + URI + "/")
        	.body(Mono.just(planetRM), PlanetsResumeModel.class)
            .exchange()
            .expectStatus().isCreated()
            .expectBody();
	}
	
	@Test
	public void testPutPlanetsUpdateOk() {
		
        Mockito
            .when(repository.update(Mono.just(planetRM), id))
            .thenReturn(Mono.just(planetRM));

        client
        	.put().uri(swapiPath + URI + "/{id}", id)
        	.body(Mono.just(planetRM), PlanetsResumeModel.class)
            .exchange()
            .expectStatus().isCreated()
            .expectBody();
	}
	
	@Test
	public void testDeletePlanetsDeleteOk() {
		
        Mockito
            .when(repository.delete(id))
            .thenReturn(Mono.empty());

        client
        	.delete().uri(swapiPath + URI + "/{id}", id)
            .exchange()
            .expectStatus().isNoContent()
            .expectBody();
	}
}
