package com.example.sisrec.models;

import com.example.sisrec.enums.StatusReclamacao;
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
    private AdminModel adminModel;

    @ManyToOne
    private UsuarioModel usuarioModel;
    public ReclamacaoModel() {
        super();
    }

    public ReclamacaoModel(UUID idReclamacao, LocalDate dataAberturaReclamacao, LocalDate dataEncerramentoReclamacao, String local, String titulo, String descricao, String sugestaoResolucao, StatusReclamacao statusReclamacao, AdminModel adminModel, UsuarioModel usuarioModel) {
        this.idReclamacao = idReclamacao;
        this.dataAberturaReclamacao = dataAberturaReclamacao;
        this.dataEncerramentoReclamacao = dataEncerramentoReclamacao;
        this.local = local;
        this.titulo = titulo;
        this.descricao = descricao;
        this.sugestaoResolucao = sugestaoResolucao;
        this.statusReclamacao = statusReclamacao;
        this.adminModel = adminModel;
        this.usuarioModel = usuarioModel;
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
