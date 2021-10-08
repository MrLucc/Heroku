package com.baependi.projetoIntegrador.models.exceptions;

public class excecaoIdCategoriaNaoExistente extends RuntimeException {

	/*
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public excecaoIdCategoriaNaoExistente(Long idCategoria) {
		super("Id" + idCategoria + "não existe");
	}
}
