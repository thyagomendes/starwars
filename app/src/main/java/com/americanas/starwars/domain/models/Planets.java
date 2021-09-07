package com.americanas.starwars.domain.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor  
@NoArgsConstructor
@Builder
@Document(collection = "planets")
public class Planets {

	@Id
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
