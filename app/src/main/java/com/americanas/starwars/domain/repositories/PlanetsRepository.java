package com.americanas.starwars.domain.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.americanas.starwars.domain.models.Planets;

import reactor.core.publisher.Flux;

@Repository
public interface PlanetsRepository extends ReactiveMongoRepository<Planets, String> {
	Flux<Planets> findByNameContainsIgnoreCase(String name);
}
