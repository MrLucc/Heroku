package com.BlogPessoal.projeto.generation.modelos.utilidades;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Setter
@Getter
public class UsuarioDTO {
    private @NotBlank @Email String email;
    private @NotBlank String senha;

    private Long id;
    private String nome;
    private String token;
    private String foto;
    private String tipoUsuario;
}
