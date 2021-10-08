package com.baependi.projetoIntegrador.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.baependi.projetoIntegrador.models.Categoria;

@Repository
public interface RepositorioCategoria extends JpaRepository<Categoria, Long> {

	public List<Categoria> findAllByTipoProdutoContainingIgnoreCase(String tipoProduto);


}
