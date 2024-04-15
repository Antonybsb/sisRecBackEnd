package com.example.sisrec.models;

import com.example.sisrec.enums.Perfil;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Entity
@Getter
@Setter
public class UsuarioModel extends PessoaModel {

    @OneToMany(mappedBy = "usuarioModel")
    private List<ReclamacaoModel> reclamacoes = new ArrayList<>();

    public UsuarioModel() {
        super();
        addPerfil(Perfil.USUARIO);
    }

    public UsuarioModel(UUID idPessoa, String nome, String cpf, String email, String senha) {
        super(idPessoa, nome, cpf, email, senha);
        addPerfil(Perfil.USUARIO);
    }

}
