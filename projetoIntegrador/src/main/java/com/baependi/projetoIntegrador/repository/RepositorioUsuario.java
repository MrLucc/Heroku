package com.baependi.projetoIntegrador.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.baependi.projetoIntegrador.models.Usuario;

@Repository
public interface RepositorioUsuario extends JpaRepository<Usuario, Long> {

	public List<Usuario> findAllByNomeUsuarioContainingIgnoreCase(String nomeUsuario);

	Optional<Usuario> findByEmail(String email);
}
