package com.americanas.starwars.api.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor  
@NoArgsConstructor
@Builder
public class PlanetsResumeModel {

	private String id;
	@NotNull
	@Min(value = 1)
	private int swapi_id;
	@NotNull
	private String name;
	@NotNull
	private String climate;
	@NotNull
	private String terrain;
}
