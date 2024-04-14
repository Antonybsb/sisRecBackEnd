package com.example.sisrec.domain.enums;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Inheritance(strategy = InheritanceType.JOINED)
@Entity
@Table(name = "TB_PESSOA")
@Getter
@Setter

public abstract class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected UUID idPessoa;
    protected String nome;
    protected String cpf;
    protected String email;
    protected String senha;
    protected Set<Integer> perfis = new HashSet<>();
    protected LocalDate dataCriacao = LocalDate.now();

    public Pessoa() {
        super();
    }

    public Pessoa(UUID idPessoa, String nome, String cpf, String email, String senha) {
        this.idPessoa = idPessoa;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
    }
}
