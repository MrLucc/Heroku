package com.baependi.projetoIntegrador.manipulators;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.baependi.projetoIntegrador.models.exceptions.excecaoEmailExistente;
import com.baependi.projetoIntegrador.models.exceptions.excecaoErroEmailOuSenhaExistente;
import com.baependi.projetoIntegrador.models.exceptions.excecaoIdCategoriaNaoExistente;
import com.baependi.projetoIntegrador.models.exceptions.excecaoIdProdutoNaoExistente;
import com.baependi.projetoIntegrador.models.exceptions.excecaoIdUsuarioNaoExistente;

@ControllerAdvice
public class exceptionManipulator {

	@ExceptionHandler(excecaoEmailExistente.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ResponseBody
	public Map<String, String> emailExistenteNotFoundHandler(excecaoEmailExistente erroEmail) {

		Map<String, String> objetoErro = new HashMap<>();

		objetoErro.put("status", "400");
		objetoErro.put("Mensagem", "E-mail existente! Entre com um correto ou faça um cadastro");
		objetoErro.put("Problema", erroEmail.getMessage());

		return objetoErro;

	}

	@ExceptionHandler(excecaoErroEmailOuSenhaExistente.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ResponseBody
	public Map<String, String> erroEmailOuSenhaInvalidoNotFoundHanlder(
			excecaoErroEmailOuSenhaExistente erroEmailOuSenha) {

		Map<String, String> objetoErro = new HashMap<>();

		objetoErro.put("status", "400");
		objetoErro.put("Mensagem", "E-mail ou senha inválidos! Tente novamente.");
		objetoErro.put("Problema", erroEmailOuSenha.getMessage());

		return objetoErro;

	}

	@ExceptionHandler(excecaoIdCategoriaNaoExistente.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ResponseBody
	public Map<String, String> erroIdCategoriaNaoExistenteNotFoundHandler(
			excecaoIdCategoriaNaoExistente erroIdCategoria) {

		Map<String, String> objetoErro = new HashMap<>();

		objetoErro.put("status", "400");
		objetoErro.put("Mensagem", "Id de categoria não encontrado. Tente novamente com outro Id.");
		objetoErro.put("Problema", erroIdCategoria.getMessage());

		return objetoErro;

	}

	@ExceptionHandler(excecaoIdProdutoNaoExistente.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ResponseBody
	public Map<String, String> erroEmailOuSenhaInvalidoNotFoundHanlder(excecaoIdProdutoNaoExistente erroIdProduto) {

		Map<String, String> objetoErro = new HashMap<>();

		objetoErro.put("status", "400");
		objetoErro.put("Mensagem", "Id de produto não encontrado. Tente novamente com outro Id");
		objetoErro.put("Problema", erroIdProduto.getMessage());

		return objetoErro;

	}

	@ExceptionHandler(excecaoIdUsuarioNaoExistente.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ResponseBody
	public Map<String, String> erroEmailOuSenhaInvalidoNotFoundHanlder(excecaoIdUsuarioNaoExistente erroIdUsuario) {

		Map<String, String> objetoErro = new HashMap<>();

		objetoErro.put("status", "400");
		objetoErro.put("Mensagem", "Id de usuario não encontrado. Tente novamente com outro Id");
		objetoErro.put("Problema", erroIdUsuario.getMessage());

		return objetoErro;

	}

}
