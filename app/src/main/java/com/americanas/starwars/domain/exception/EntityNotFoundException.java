package com.americanas.starwars.domain.exception;

public abstract class EntityNotFoundException extends BusinessException {

	private static final long serialVersionUID = 1L;

	public EntityNotFoundException(String mensagem) {
		super(mensagem);
	}
	
}
