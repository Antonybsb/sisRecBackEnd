package com.example.sisrec.domain.enums;

import com.example.sisrec.models.ReclamacaoModel;
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
public class Admin extends Pessoa{

    @OneToMany(mappedBy = "admin")
    private List<ReclamacaoModel> reclamacaoModels = new ArrayList<>();

    public Admin() {
        super();
    }

    public Admin(UUID idPessoa, String nome, String cpf, String email, String senha) {
        super(idPessoa, nome, cpf, email, senha);
    }
}
