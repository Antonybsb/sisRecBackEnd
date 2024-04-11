package com.example.sisrec.models;

import com.example.sisrec.domain.enums.StatusReclamacao;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "TB_RECLAMACAO")
@Getter
@Setter
public class ReclamacaoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idReclamacao;
    private String descricao;
    private String localizacao;
    private Date dataReclamacao;


    @ManyToOne
    @JoinColumn(name = "categoria_id_categoria")
    private CategoriaModel categoria;

    @ManyToOne
    @JoinColumn(name = "usuario_id_usuario")
    private UsuarioModel usuario;

    @Enumerated(EnumType.STRING)
    private StatusReclamacao status;


}
