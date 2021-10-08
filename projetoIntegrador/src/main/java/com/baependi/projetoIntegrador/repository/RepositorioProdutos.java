package com.baependi.projetoIntegrador.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.baependi.projetoIntegrador.models.Produtos;

@Repository
public interface RepositorioProdutos extends JpaRepository<Produtos, Long> {

	public List<Produtos> findAllByNomeProdutoContainingIgnoreCase(String nomeProduto);

}
