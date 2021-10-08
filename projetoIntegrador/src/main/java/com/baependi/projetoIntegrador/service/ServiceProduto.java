package com.baependi.projetoIntegrador.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baependi.projetoIntegrador.models.Categoria;
import com.baependi.projetoIntegrador.models.Produtos;
import com.baependi.projetoIntegrador.repository.RepositorioCategoria;
import com.baependi.projetoIntegrador.repository.RepositorioProdutos;
import com.baependi.projetoIntegrador.repository.RepositorioUsuario;

@Service
public class ServiceProduto {

	@Autowired
	private RepositorioProdutos repositoryP;

	@Autowired
	private RepositorioCategoria repositoryC;

	@Autowired
	private RepositorioUsuario repositoryU;

	/**
	 * Metódo usado para alterar um produto. O mesmo retorna um Optional com os
	 * atributos de produto, caso correto ou um Optional.empty() caso alguma
	 * informação digitada não exista.
	 * 
	 * @param produtoParaAlterar com os atributos de produto
	 * @return Optional com as informações do produto alteradas
	 * @since 1.5
	 * @author Equipe 6
	 */

	public Optional<Produtos> alterarProduto(Produtos produtoParaAlterar) {
		return repositoryP.findById(produtoParaAlterar.getIdProduto()).map(produtoExistente -> {
			produtoExistente.setNomeProduto(produtoParaAlterar.getNomeProduto());
			produtoExistente.setPrecoProduto(produtoParaAlterar.getPrecoProduto());
			produtoExistente.setDescricaoProduto(produtoParaAlterar.getDescricaoProduto());
			produtoExistente.setAutoreProduto(produtoParaAlterar.getAutoreProduto());
			produtoExistente.setTipoMaterial(produtoParaAlterar.getTipoMaterial());
			produtoExistente.setArtesanal(produtoParaAlterar.getArtesanal());
			return Optional.ofNullable(repositoryP.save(produtoExistente));
		}).orElseGet(() -> {
			return Optional.empty();
		});
	}

	public Optional<?> cadastrarProduto(Produtos novoProduto) {
		Optional<Categoria> objetoProduto = repositoryC.findById(novoProduto.getCodigoCategoria().getIdCategoria());
		return repositoryU.findById(novoProduto.getComprador().getIdUsuario()).map(produtoExistente -> {
			if (objetoProduto.isPresent()) {
				novoProduto.setComprador(produtoExistente);
				novoProduto.setCodigoCategoria(objetoProduto.get());
				return Optional.ofNullable(repositoryP.save(novoProduto));
			} else {
				return Optional.empty();
			}

		}).orElseGet(() -> {
			return Optional.empty();
		});

	}

}
