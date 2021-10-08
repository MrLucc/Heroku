package com.baependi.projetoIntegrador.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;

@Entity
@Table(name = "tb_produtos")

public class Produtos {

	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long idProduto;
	private @NotBlank @Size(min = 3, max = 255) String nomeProduto;
	private @NotNull float precoProduto;
	private @NotBlank String descricaoProduto;
	private @NotBlank String autoreProduto;
	private String artesanal;
	private @NotBlank String tipoMaterial;



	@ManyToOne
	@JoinColumn(name = "categoriaProdutos_id")
	@JsonIgnoreProperties({ "categoriaProdutos" })
	private Categoria codigoCategoria;

	@ManyToOne
	@JoinColumn(name = "comprador_id")
	@JsonIgnoreProperties({ "listaDeProdutos" })
	private Usuario comprador;

	public Long getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Long idProduto) {
		this.idProduto = idProduto;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	public float getPrecoProduto() {
		return precoProduto;
	}

	public void setPrecoProduto(float precoProduto) {
		this.precoProduto = precoProduto;
	}

	public String getDescricaoProduto() {
		return descricaoProduto;
	}

	public void setDescricaoProduto(String descricaoProduto) {
		this.descricaoProduto = descricaoProduto;
	}

	public String getAutoreProduto() {
		return autoreProduto;
	}

	public void setAutoreProduto(String autoreProduto) {
		this.autoreProduto = autoreProduto;
	}

	public Categoria getCodigoCategoria() {
		return codigoCategoria;
	}

	public void setCodigoCategoria(Categoria codigoCategoria) {
		this.codigoCategoria = codigoCategoria;
	}

	public Usuario getComprador() {
		return comprador;
	}

	public void setComprador(Usuario comprador) {
		this.comprador = comprador;
	}
	public String getArtesanal() {
		return artesanal;
	}

	public void setArtesanal(String artesanal) {
		this.artesanal = artesanal;
	}

	public String getTipoMaterial() {
		return tipoMaterial;
	}

	public void setTipoMaterial(String tipoMaterial) {
		this.tipoMaterial = tipoMaterial;
	}

	
}
