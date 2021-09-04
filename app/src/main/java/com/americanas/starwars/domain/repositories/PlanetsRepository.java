package com.americanas.starwars.domain.repositories;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.americanas.starwars.domain.models.Planets;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PlanetsRepository extends ReactiveCrudRepository<Planets, Long> {
 
    Flux<Planets> findAll();
    Flux<Planets> findAllByName(String name);
    Mono<Planets> findById(Long id);
}
