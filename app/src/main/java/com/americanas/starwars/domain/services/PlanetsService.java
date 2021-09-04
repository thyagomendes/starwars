package com.americanas.starwars.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Service;

import com.americanas.starwars.domain.models.Planets;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PlanetsService {

	@Autowired
    ReactiveMongoTemplate template;
	
	public Flux<Planets> findAll( Pageable pageable){
		return template.findAll(Planets.class);
	}
	
	public Mono<Planets> findById(String id){
		return template.findById(id, Planets.class);
	}
}
