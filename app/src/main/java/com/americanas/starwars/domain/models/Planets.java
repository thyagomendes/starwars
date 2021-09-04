package com.americanas.starwars.domain.models;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
public class Planets {

	@Id
	private String id;
	@NotNull
	private int swapi_id;
	@NotNull
	private String name;
	@NotNull
	private String climate;
	@NotNull
	private String terrain;
}
