package com.americanas.starwars.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.americanas.starwars.domain.models.Planets;
import com.americanas.starwars.domain.services.PlanetsService;

import reactor.core.publisher.Flux;

@RestController
@RequestMapping(path = "/planets", produces = MediaType.APPLICATION_JSON_VALUE)
public class PlanetsController {

	@Autowired
	PlanetsService planetsService;
	
	@GetMapping
	public Flux<Planets> findAll(Pageable pageable) {
		return planetsService.findAll(pageable);
	}
}
