package com.example.sisrec.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "TB_CATEGORIA")
@Getter
@Setter
public class CategoriaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idCategoria;
    private String nome;
    private String descricao;

    @OneToMany(mappedBy = "categoria")
    private List<ReclamacaoModel> reclamacoes;
}
