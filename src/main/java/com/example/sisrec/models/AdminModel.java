package com.example.sisrec.models;

import com.example.sisrec.dtos.AdminRecordDto;
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
public class AdminModel extends PessoaModel {

    @OneToMany(mappedBy = "adminModel")
    private List<ReclamacaoModel> reclamacaoModels = new ArrayList<>();

    public AdminModel() {
        super();
        addPerfil(Perfil.ADMIN);
    }




    public AdminModel(UUID idPessoa, String nome, String cpf, String email, String senha) {
        super(idPessoa, nome, cpf, email, senha);
        addPerfil(Perfil.ADMIN);
    }
}
