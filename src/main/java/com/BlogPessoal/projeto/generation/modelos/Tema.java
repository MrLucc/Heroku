package com.BlogPessoal.projeto.generation.modelos;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Tema {
    private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long idTema;
    private @NotBlank String tema;

   @OneToMany(mappedBy = "temaRelacionado", cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties({"temaRelacionado"})
    private List<Postagem> postagens = new ArrayList<>();
}
