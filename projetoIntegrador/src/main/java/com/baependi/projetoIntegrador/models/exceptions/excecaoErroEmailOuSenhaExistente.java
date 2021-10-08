package com.baependi.projetoIntegrador.models.exceptions;

public class excecaoErroEmailOuSenhaExistente extends RuntimeException {
	
	/*
	 * 
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public excecaoErroEmailOuSenhaExistente() {
		super("E-mail ou senha inválidos!");
	}
}
