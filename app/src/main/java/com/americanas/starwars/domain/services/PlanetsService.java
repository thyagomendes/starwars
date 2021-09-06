package com.americanas.starwars.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.americanas.starwars.api.models.PlanetsModel;
import com.americanas.starwars.api.models.PlanetsResumeModel;
import com.americanas.starwars.api.models.PlanetsSwapiModel;
import com.americanas.starwars.api.utils.AppUtils;
import com.americanas.starwars.domain.repositories.PlanetsRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PlanetsService {

	@Autowired
	PlanetsRepository planetsRepository;
	
	@Autowired
	private WebClient webClientSwapi;
	
	@Value("${app.swapi.planets}")
	private String URI;
	
	public Flux<PlanetsResumeModel> findAll(){
		return planetsRepository.findAll()
				.map(AppUtils::entityToResumeModel);
	}
	
	public Mono<PlanetsModel> findById(String id){
		return planetsRepository.findById(id)
				.map(AppUtils::entityToModel)
				.flatMap(planetsModel -> findPlanetsSwapi(planetsModel.getSwapi_id())
						.doOnNext(planetsSwApi -> planetsModel.setFilmsCount(planetsSwApi.getFilms().size()))
						.map(planetsSwApi -> planetsModel ));
	}
	
	public Flux<PlanetsResumeModel> findByName(String name){
		return planetsRepository.findByNameContainsIgnoreCase(name)
				.map(AppUtils::entityToResumeModel);
	}
	
	public Mono<PlanetsResumeModel> create(Mono<PlanetsResumeModel> planetResumeModel){
		return planetResumeModel.map(AppUtils::resumeModelToEntity)
				.flatMap(planetsRepository::insert)
				.map(AppUtils::entityToResumeModel);
	}
	
	public Mono<PlanetsResumeModel> update(Mono<PlanetsResumeModel> planetResumeModel, String id){
		return planetsRepository.findById(id)
				.flatMap(p -> planetResumeModel.map(AppUtils::resumeModelToEntity)
						.doOnNext(planets -> planets.setId(id)))
				.flatMap(planetsRepository::save)
				.map(AppUtils::entityToResumeModel);
	}
	
	public Mono<Void> delete(String id){
		return planetsRepository.deleteById(id);
	}
	
	private Mono<PlanetsSwapiModel> findPlanetsSwapi(int planetId) {
		return this.webClientSwapi
				.method(HttpMethod.GET)
				.uri(URI + "/{patientId}", planetId)
				.retrieve()
				.bodyToMono(PlanetsSwapiModel.class)
				.log();
	}
}
