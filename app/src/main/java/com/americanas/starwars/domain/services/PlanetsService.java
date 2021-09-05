package com.americanas.starwars.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.americanas.starwars.api.models.PlanetsModel;
import com.americanas.starwars.api.utils.AppUtils;
import com.americanas.starwars.domain.repositories.PlanetsRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PlanetsService {

	@Autowired
	PlanetsRepository planetsRepository;
	
	public Flux<PlanetsModel> findAll(){
		return planetsRepository.findAll()
				.map(AppUtils::entityToModel);
	}
	
	public Mono<PlanetsModel> findById(String id){
		return planetsRepository.findById(id)
				.map(AppUtils::entityToModel);
	}
	
	public Mono<PlanetsModel> create(Mono<PlanetsModel> planetModel){
		return planetModel.map(AppUtils::modelToEntity)
				.flatMap(planetsRepository::insert)
				.map(AppUtils::entityToModel);
	}
	
	public Mono<PlanetsModel> update(Mono<PlanetsModel> planetModel, String id){
		return planetsRepository.findById(id)
				.flatMap(p -> planetModel.map(AppUtils::modelToEntity)
						.doOnNext(e -> e.setId(id)))
				.flatMap(planetsRepository::save)
				.map(AppUtils::entityToModel);
	}
	
	public Mono<Void> delete(String id){
		return planetsRepository.deleteById(id);
	}
}
