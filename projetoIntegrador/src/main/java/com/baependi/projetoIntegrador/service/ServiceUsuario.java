package com.baependi.projetoIntegrador.service;

import java.nio.charset.Charset;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.apache.commons.codec.binary.Base64;
import com.baependi.projetoIntegrador.models.Usuario;
import com.baependi.projetoIntegrador.models.utilidades.UsuarioEspelho;
import com.baependi.projetoIntegrador.repository.RepositorioUsuario;

@Service
public class ServiceUsuario {

	@Autowired
	private RepositorioUsuario repository;

	public Optional<Object> cadastraUsuario(Usuario usuario) {
		return repository.findByEmail(usuario.getEmail()).map(novoUsuario -> {
			return Optional.empty();
		}).orElseGet(() -> {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			String result = encoder.encode(usuario.getSenha());
			usuario.setSenha(result);
			return Optional.ofNullable(repository.save(usuario));
		});
	}

	public Optional<?> pegarUsuarioCadastrado(UsuarioEspelho usuarioParaCadastrar) {
		return repository.findByEmail(usuarioParaCadastrar.getEmail()).map(usuarioExistente -> {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

			if (encoder.matches(usuarioParaCadastrar.getSenha(), usuarioExistente.getSenha())) {

				String estruturaLiteral = usuarioParaCadastrar.getEmail() + ":" + usuarioParaCadastrar.getSenha();
				byte[] autorizaEstrutura = Base64.encodeBase64(estruturaLiteral.getBytes(Charset.forName("US-ASCII")));
				String token = "Basic " + new String(autorizaEstrutura);

				usuarioParaCadastrar.setToken(token);
				usuarioParaCadastrar.setIdUsuario(usuarioExistente.getIdUsuario());
				usuarioParaCadastrar.setNomeUsuario(usuarioExistente.getNomeUsuario());
				usuarioParaCadastrar.setSenha(usuarioExistente.getSenha());
				usuarioParaCadastrar.setCpf(usuarioExistente.getSenha());
				usuarioParaCadastrar.setEstado(usuarioExistente.getEstado());
				usuarioParaCadastrar.setCidade(usuarioExistente.getCidade());
				usuarioParaCadastrar.setEndereco(usuarioExistente.getEndereco());
				usuarioParaCadastrar.setFoto(usuarioExistente.getSenha());
				usuarioParaCadastrar.setTipoUsuario(usuarioExistente.getTipoUsuario());
				return Optional.ofNullable(token);

			} else {

				return Optional.empty();
			}
		}).orElseGet(() -> {

			return Optional.empty();
		});
	}

	public Optional<?> atualizarUsuario(UsuarioEspelho usuarioParaAlterar) {
		return repository.findById(usuarioParaAlterar.getIdUsuario()).map(usuarioExistente -> {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

			String senhaEncriptada = encoder.encode(usuarioParaAlterar.getSenha());
			usuarioExistente.setNomeUsuario(usuarioParaAlterar.getNomeUsuario());
			usuarioExistente.setSenha(senhaEncriptada);

			return Optional.ofNullable(repository.save(usuarioExistente));
		}).orElseGet(() -> {
			return Optional.empty();
		});
	}

}
