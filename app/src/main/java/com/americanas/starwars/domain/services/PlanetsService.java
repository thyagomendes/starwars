package com.americanas.starwars.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.americanas.starwars.domain.models.Planets;
import com.americanas.starwars.domain.repositories.PlanetsRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PlanetsService {

	@Autowired
	PlanetsRepository planetsRepository;
	
	public Flux<Planets> findAll(){
		return planetsRepository.findAll();
	}
	
	public Mono<Planets> findById(String id){
		return planetsRepository.findById(id);
	}
	
	public Mono<Planets> create(Planets planet){
		return planetsRepository.save(planet);
	}
	
	public Mono<Void> delete(String id){
		return planetsRepository.deleteById(id);
	}
}
