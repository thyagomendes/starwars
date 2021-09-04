package com.americanas.starwars.domain.repositories;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.americanas.starwars.domain.models.Planets;

@Repository
public interface PlanetsRepository extends ReactiveCrudRepository<Planets, String> {

}
