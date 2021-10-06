package com.BlogPessoal.projeto.generation.modelos;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
public class Usuario {
    private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long idUsuario;
    private @NotBlank String nome;
    private @NotBlank @Email String email;
    private @NotBlank @Size(min = 5) String senha;
    private String foto;
    private String tipoUsuario;

    @OneToMany(mappedBy = "criador", cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties({"criador"})
    private List<Postagem> minhasPostagens = new ArrayList<>();

    public Usuario(Long idUsuario, String nome, String email, String senha) {
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public Usuario() { }

}
