package com.example.sisrec.models;

import com.example.sisrec.enums.Perfil;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Inheritance(strategy = InheritanceType.JOINED)
@Entity
@Table(name = "TB_PESSOA")
@Getter
@Setter

public abstract class PessoaModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected UUID idPessoa;
    protected String nome;
    @Column(unique = true)
    protected String cpf;
    @Column(unique = true)
    protected String email;
    protected String senha;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "PERFIS")
    protected Set<Integer> perfis = new HashSet<>();

    @JsonFormat(pattern = "dd/MM/yyyy")
    protected LocalDate dataCriacao = LocalDate.now();

    public PessoaModel() {
        super();
        addPerfil(Perfil.USUARIO);
    }

    public void addPerfil(Perfil perfil) {
        this.perfis.add(perfil.getCodigo());
    }

    public PessoaModel(UUID idPessoa, String nome, String cpf, String email, String senha) {
        this.idPessoa = idPessoa;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
        addPerfil(Perfil.USUARIO);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PessoaModel that)) return false;
        return idPessoa.equals(that.idPessoa) && cpf.equals(that.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPessoa, cpf);
    }
}
