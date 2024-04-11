package com.example.sisrec.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "TB_USUARIO")
@Getter
@Setter
public class UsuarioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idUsuario;
    private String nome;
    private String email;
    private String senha;

    @OneToMany(mappedBy = "usuario")
    private List<ReclamacaoModel> reclamacoes;


}
