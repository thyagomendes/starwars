package com.americanas.starwars.api.controllers;

import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.americanas.starwars.api.models.PlanetsModel;
import com.americanas.starwars.api.models.PlanetsResumeModel;
import com.americanas.starwars.domain.exception.EntityNotFoundException;
import com.americanas.starwars.domain.services.PlanetsService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = "/planets", produces = MediaType.APPLICATION_JSON_VALUE)
public class PlanetsController {

	@Autowired
	PlanetsService planetsService;

	@GetMapping("/")
	@ResponseStatus(HttpStatus.OK)
	public Flux<PlanetsResumeModel> findAll() {
		return planetsService.findAll();
	}

	@GetMapping(params = "search")
	@ResponseStatus(HttpStatus.OK)
	public Flux<PlanetsResumeModel> findByName(@RequestParam("search") String search) {
		return planetsService.findByName(search);
	}

	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Mono<PlanetsModel> findById(@PathVariable String id) {
		Mono<PlanetsModel> planetsModel = planetsService.findById(id)
				.switchIfEmpty(Mono.error(new EntityNotFoundException("Não existe planeta com código: " + id)));
		
		return planetsModel;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Mono<PlanetsResumeModel> create(@Valid @RequestBody Mono<PlanetsResumeModel> planetModel) {
		return planetsService.create(planetModel);
	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Mono<PlanetsResumeModel> update(@Valid @RequestBody Mono<PlanetsResumeModel> planetModel, @PathVariable String id) {
		return planetsService.update(planetModel, id);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public Mono<Void> delete(@PathVariable String id) {
		return planetsService.delete(id);
	}
}
