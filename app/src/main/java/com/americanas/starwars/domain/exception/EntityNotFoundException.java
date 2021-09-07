package com.americanas.starwars.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class EntityNotFoundException extends ResponseStatusException {

	private static final long serialVersionUID = 1L;

	public EntityNotFoundException(String mensagem) {
		super(HttpStatus.NOT_FOUND, mensagem);
	}
}