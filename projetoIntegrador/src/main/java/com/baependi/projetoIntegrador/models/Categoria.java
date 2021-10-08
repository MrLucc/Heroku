package com.baependi.projetoIntegrador.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_categoria")

public class Categoria {

	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long idCategoria;
	private @NotBlank String tipoProduto;


	@OneToMany(mappedBy = "codigoCategoria", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties({ "codigoCategoria" })
	private List<Produtos> categoriaProdutos = new ArrayList<>();

	public Long getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Long idCategoria) {
		this.idCategoria = idCategoria;
	}


	public String getTipoProduto() {
		return tipoProduto;
	}

	public void setTipoProduto(String tipoProduto) {
		this.tipoProduto = tipoProduto;
	}


	public List<Produtos> getCategoriaProdutos() {
		return categoriaProdutos;
	}

	public void setCategoriaProdutos(List<Produtos> categoriaProdutos) {
		this.categoriaProdutos = categoriaProdutos;
	}

}
