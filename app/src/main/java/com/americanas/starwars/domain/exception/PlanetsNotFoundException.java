package com.americanas.starwars.domain.exception;

public class PlanetsNotFoundException extends EntityNotFoundException {

	private static final long serialVersionUID = 1L;

	public PlanetsNotFoundException(String mensagem) {
		super(mensagem);
	}
}