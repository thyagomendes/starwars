package com.americanas.starwars.domain.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.americanas.starwars.domain.models.Planets;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface PlanetsRepository extends ReactiveCrudRepository<Planets, Long> {
 
    Flux<Planets> findAll(Pageable pageable);
    Flux<Planets> findAllByName(Pageable pageable, String name);
    Mono<Planets> findById(Long id);
}
