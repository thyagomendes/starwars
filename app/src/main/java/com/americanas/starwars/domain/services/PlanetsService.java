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
import com.americanas.starwars.domain.exception.PlanetsNotFoundException;
import com.americanas.starwars.domain.repositories.PlanetsRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PlanetsService {
	
	@Autowired
	PlanetsRepository planetsRepository;
	
	@Value("${app.swapi.planets}")
	private String URI;
	
	@Value("${app.swapi.rootPath}")
	private String swapiPath;

//	@Autowired
	private WebClient webClientSwApi;

	public Flux<PlanetsResumeModel> findAll(){
		return planetsRepository.findAll()
				.flatMap(planets -> Mono.just(AppUtils.entityToResumeModel(planets)));
	}
	
	public Mono<PlanetsModel> findById(String id){
		return planetsRepository.findById(id)
				.switchIfEmpty(Mono.error(new PlanetsNotFoundException(id)))
				.flatMap(planets -> Mono.just(AppUtils.entityToModel(planets)))
				.flatMap(planetsModel -> findPlanetsSwapi(planetsModel.getSwapi_id())
						.doOnNext(planetsSwApi -> planetsModel.setFilmsCount(planetsSwApi.getFilms().size()))
						.map(planetsSwApi -> planetsModel));
	}
	
	public Flux<PlanetsResumeModel> findByName(String name){
		return planetsRepository.findByNameContainsIgnoreCase(name)
				.flatMap(planets -> Mono.just(AppUtils.entityToResumeModel(planets)));
	}
	
	public Mono<PlanetsResumeModel> create(Mono<PlanetsResumeModel> planetResumeModel){
		return planetResumeModel.map(AppUtils::resumeModelToEntity)
				.flatMap(planetsRepository::insert)
				.flatMap(planets -> Mono.just(AppUtils.entityToResumeModel(planets)));
	}
	
	public Mono<PlanetsResumeModel> update(Mono<PlanetsResumeModel> planetResumeModel, String id){
		return planetsRepository.findById(id)
				.switchIfEmpty(Mono.error(new PlanetsNotFoundException(id)))
				.flatMap(p -> planetResumeModel.map(AppUtils::resumeModelToEntity)
						.doOnNext(planets -> planets.setId(id)))
				.flatMap(planetsRepository::save)
				.flatMap(planets -> Mono.just(AppUtils.entityToResumeModel(planets)));
	}
	
	public Mono<Void> delete(String id){
		return planetsRepository.findById(id)
				.switchIfEmpty(Mono.error(new PlanetsNotFoundException(id)))
				.flatMap(p -> planetsRepository.deleteById(id));
	}
	
	private Mono<PlanetsSwapiModel> findPlanetsSwapi(int planetId) {
//		this.webClientSwApi = WebClient.create(swapiPath);
		return WebClient.create(swapiPath)
				.method(HttpMethod.GET)
				.uri(URI + "/{planetId}", planetId)
				.retrieve()
				.bodyToMono(PlanetsSwapiModel.class);
	}
}