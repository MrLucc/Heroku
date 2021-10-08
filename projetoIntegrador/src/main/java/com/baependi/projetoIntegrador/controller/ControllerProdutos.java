package com.baependi.projetoIntegrador.controller;

import com.baependi.projetoIntegrador.models.Produtos;
import com.baependi.projetoIntegrador.models.exceptions.excecaoIdProdutoNaoExistente;
import com.baependi.projetoIntegrador.repository.RepositorioProdutos;
import com.baependi.projetoIntegrador.service.ServiceProduto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/baependi/produtos")
@CrossOrigin(origins = "", allowedHeaders = "")
public class ControllerProdutos {

	private @Autowired RepositorioProdutos repositorio;
	private @Autowired ServiceProduto servico;

	@GetMapping("/buscarTodos")
	private ResponseEntity<List<Produtos>> GetAll() {
		return ResponseEntity.ok(repositorio.findAll());
	}

	@GetMapping("/BuscarProdutoId/{idProduto}")
	private ResponseEntity<Produtos> findById(@PathVariable(value = "idProduto") Long idProduto) {
		Optional<Produtos> objetoProduto = repositorio.findById(idProduto);

		if (objetoProduto.isPresent()) {
			return ResponseEntity.status(200).body(objetoProduto.get());
		} else {
			return ResponseEntity.status(204).build();
		}

	}

	@GetMapping("/acharPorNomeProduto/{nomeProduto}")
	private ResponseEntity<List<Produtos>> acharPorNome(@PathVariable(value = "nomeProduto") String nomeProduto) {
		List<Produtos> objetoProduto = repositorio.findAllByNomeProdutoContainingIgnoreCase(nomeProduto);
		if (objetoProduto.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(201).body(objetoProduto);
		}
	}

	@PostMapping("/salvar")
	private ResponseEntity<Object> salvarProduto(@Valid @RequestBody Produtos produtoSalvo) {
		Optional<?> objetoSalvar = servico.cadastrarProduto(produtoSalvo);
		if (objetoSalvar.isPresent()) {
			return ResponseEntity.status(201).body(objetoSalvar);
		} else {
			return ResponseEntity.status(204).build();
		}
	}

	@PutMapping("/atualizar")
	private ResponseEntity<Object> atualizarProduto(@Valid @RequestBody Produtos produtoAtualizado) {
		Optional<Produtos> objetoAtualizar = servico.alterarProduto(produtoAtualizado);
		if (objetoAtualizar.isPresent()) {
			return ResponseEntity.status(201).body(objetoAtualizar.get());
		} else {
			throw new excecaoIdProdutoNaoExistente(produtoAtualizado.getIdProduto());
		}
	}

	@DeleteMapping("/deletar/{idProduto}")
	private void deletarProduto(@PathVariable(value = "idProduto") Long idProduto) {
		repositorio.deleteById(idProduto);
	}
}
