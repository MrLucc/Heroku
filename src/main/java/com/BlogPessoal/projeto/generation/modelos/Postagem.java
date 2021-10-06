package com.BlogPessoal.projeto.generation.modelos;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Getter
@Setter
public class Postagem {
    private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long idPostagem;
    private @NotBlank String titulo;
    private @NotBlank String descricao;

    @ManyToOne
    @JoinColumn(name = "criador_id")
    @JsonIgnoreProperties({"minhasPostagens"})
    private Usuario criador;

    @ManyToOne
    @JoinColumn(name = "tema_id")
    @JsonIgnoreProperties({"postagens"})
    private Tema temaRelacionado;

}
