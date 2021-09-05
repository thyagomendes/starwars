package com.americanas.starwars.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.americanas.starwars.api.models.PlanetsModel;
import com.americanas.starwars.domain.services.PlanetsService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = "/planets", produces = MediaType.APPLICATION_JSON_VALUE)
public class PlanetsController {

	@Autowired
	PlanetsService planetsService;
	
	@GetMapping
	public Flux<PlanetsModel> findAll() {
		return planetsService.findAll();
	}
	
	@GetMapping("/{id}")
	public Mono<PlanetsModel> findById(@PathVariable String id) {
		return planetsService.findById(id);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Mono<PlanetsModel> create(@RequestBody Mono<PlanetsModel> planetModel){
		return planetsService.create(planetModel);
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Mono<PlanetsModel> update(@RequestBody Mono<PlanetsModel> planetModel, @PathVariable String id){
		return planetsService.update(planetModel, id);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public Mono<Void> delete(@PathVariable String id) {
		return planetsService.delete(id);
	}
}
