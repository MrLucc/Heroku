package com.baependi.projetoIntegrador.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baependi.projetoIntegrador.models.Usuario;
import com.baependi.projetoIntegrador.models.exceptions.excecaoEmailExistente;
import com.baependi.projetoIntegrador.models.exceptions.excecaoErroEmailOuSenhaExistente;
import com.baependi.projetoIntegrador.models.exceptions.excecaoIdUsuarioNaoExistente;
import com.baependi.projetoIntegrador.models.utilidades.UsuarioEspelho;
import com.baependi.projetoIntegrador.repository.RepositorioUsuario;
import com.baependi.projetoIntegrador.service.ServiceUsuario;

@RestController
@RequestMapping("/baependi/usuario")
@CrossOrigin("*")
public class ControllerUsuario {

	private @Autowired RepositorioUsuario repositorio;
	private @Autowired ServiceUsuario servico;

	@GetMapping("/buscarTodos")
	private ResponseEntity<List<Usuario>> getAll() {
		return ResponseEntity.ok(repositorio.findAll());
	}

	@GetMapping("/buscarId/{buscarId}")
	private ResponseEntity<Usuario> getById(@PathVariable(value = "buscarId") Long idUsuario) {
		Optional<Usuario> objetoUsuario = repositorio.findById(idUsuario);
		if (objetoUsuario.isPresent()) {
			return ResponseEntity.status(200).body(objetoUsuario.get());
		} else {
			return ResponseEntity.status(204).build();
		}
	}

	@GetMapping("/{buscarNome}")
	private ResponseEntity<List<Usuario>> getByName(@PathVariable(value = "buscarNome") String nomeUsuario) {
		List<Usuario> objetoUsuario = repositorio.findAllByNomeUsuarioContainingIgnoreCase(nomeUsuario);

		if (objetoUsuario.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(201).body(objetoUsuario);
		}
	}

	@PostMapping("/cadastrar")
	private ResponseEntity<Object> salvarUsuario(@Valid @RequestBody Usuario novoUsuario) {
		Optional<Object> objetoUsuario = servico.cadastraUsuario(novoUsuario);

		if (objetoUsuario.isPresent()) {
			return ResponseEntity.status(201).body(objetoUsuario);

		} else {

			throw new excecaoEmailExistente(novoUsuario.getEmail());
		}
	}

	@PutMapping("/login")
	private ResponseEntity<Object> loginUsuario(@Valid @RequestBody UsuarioEspelho logarUsuario) {
		Optional<?> objetoLogin = servico.pegarUsuarioCadastrado(logarUsuario);

		if (objetoLogin.isPresent()) {

			return ResponseEntity.status(201).body(objetoLogin.get());

		} else {

			throw new excecaoErroEmailOuSenhaExistente();
		}

	}

	@PutMapping("/atualizar")
	private ResponseEntity<Object> atualizarUsuario(@Valid @RequestBody UsuarioEspelho atualizarUsuario) {
		Optional<?> objetoAtualizar = servico.atualizarUsuario(atualizarUsuario);

		if (objetoAtualizar.isPresent()) {
			return ResponseEntity.status(201).body(objetoAtualizar.get());
		} else {
			throw new excecaoIdUsuarioNaoExistente(atualizarUsuario.getIdUsuario());
		}
	}

	@DeleteMapping("/deletar/{id}")
	private void deletarUsuario(@PathVariable(value = "id") Long idUsuario) {
		repositorio.deleteById(idUsuario);
	}
}
