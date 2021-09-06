package com.americanas.starwars.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor  
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlanetsResumeModel {

	private String id;
	private int swapi_id;
	private String name;
	private String climate;
	private String terrain;
}
