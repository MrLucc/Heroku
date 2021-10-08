package com.baependi.projetoIntegrador.models.exceptions;

public class excecaoIdProdutoNaoExistente extends RuntimeException {

	/*
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public excecaoIdProdutoNaoExistente(Long idProduto) {
		super("Id" + idProduto + "não existe");
	}
}
