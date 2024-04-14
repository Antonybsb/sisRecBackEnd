package com.example.sisrec.models;

import com.example.sisrec.domain.enums.Admin;
import com.example.sisrec.domain.enums.StatusReclamacao;
import com.example.sisrec.domain.enums.Usuario;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "TB_RECLAMACAO")
@Getter
@Setter
public class ReclamacaoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idReclamacao;
    private LocalDate dataAberturaReclamacao = LocalDate.now();
    private LocalDate dataEncerramentoReclamacao;
    private String local;
    private String titulo;
    private String descricao;
    private String sugestaoResolucao;

    @Enumerated(EnumType.ORDINAL)
    private StatusReclamacao statusReclamacao;


    @ManyToOne
    private Admin admin;

    @ManyToOne
    private Usuario usuario;
    public ReclamacaoModel() {
        super();
    }

    public ReclamacaoModel(UUID idReclamacao, String local, String titulo, String descricao, String sugestaoResolucao, StatusReclamacao statusReclamacao, Admin admin, Usuario usuario) {
        this.idReclamacao = idReclamacao;
        this.local = local;
        this.titulo = titulo;
        this.descricao = descricao;
        this.sugestaoResolucao = sugestaoResolucao;
        this.statusReclamacao = statusReclamacao;
        this.admin = admin;
        this.usuario = usuario;
    }

    //    @ManyToOne
//    @JoinColumn(name = "categoria_id_categoria")
//    private CategoriaModel categoria;
//
//    @ManyToOne
//    @JoinColumn(name = "usuario_id_usuario")
//    private UsuarioModel usuario;
//
//    @Enumerated(EnumType.STRING)
//    private StatusReclamacao status;


}
