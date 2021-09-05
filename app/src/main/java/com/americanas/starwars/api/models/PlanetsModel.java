package com.americanas.starwars.api.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor  
@NoArgsConstructor
public class PlanetsModel {

	private String id;
	private int swapi_id;
	private String name;
	private String climate;
	private String terrain;
}
