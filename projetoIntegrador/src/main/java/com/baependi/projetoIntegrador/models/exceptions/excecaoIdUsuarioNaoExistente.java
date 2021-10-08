package com.baependi.projetoIntegrador.models.exceptions;

public class excecaoIdUsuarioNaoExistente extends RuntimeException {

	/*
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public excecaoIdUsuarioNaoExistente(Long idUsuario) {
		super("Id" + idUsuario + "não existe");
	}
}
