package com.baependi.projetoIntegrador.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.baependi.projetoIntegrador.models.Usuario;
import com.baependi.projetoIntegrador.repository.RepositorioUsuario;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImplements implements UserDetailsService {

	private @Autowired RepositorioUsuario repository;

	@Override
	public UserDetails loadUserByUsername(String nomeUsuario) throws UsernameNotFoundException {
		Optional<Usuario> objetoOptional = repository.findByEmail(nomeUsuario);
		if (objetoOptional.isPresent()) {
			return new UserDetailsImplements(objetoOptional.get());
		} else {
			throw new UsernameNotFoundException(nomeUsuario + "Não existe");
		}
	}
}
