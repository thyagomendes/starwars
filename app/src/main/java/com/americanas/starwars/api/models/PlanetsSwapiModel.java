package com.americanas.starwars.api.models;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PlanetsSwapiModel {

	private String name;
	private String diameter;
	private String rotation_period;
	private String orbital_period;
	private String gravity;
	private String populationString;
	private String climate;
	private String terrain;
	private String surface_water;
	private List<String> residents;
	private List<String> films;
	private String url;
	private String created;
	private String edited;
}
