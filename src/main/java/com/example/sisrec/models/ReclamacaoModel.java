package com.example.sisrec.models;

import com.example.sisrec.enums.StatusReclamacao;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "TB_RECLAMACAO")
@Getter
@Setter
public class ReclamacaoModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idReclamacao;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataAberturaReclamacao = LocalDate.now();

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataEncerramentoReclamacao;
    private String local;
    private String titulo;
    private String descricao;
    private String sugestaoResolucao;

    @Enumerated(EnumType.ORDINAL)
    private StatusReclamacao statusReclamacao;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private AdminModel adminModel;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private UsuarioModel usuarioModel;
    public ReclamacaoModel() {
        super();
    }

    public ReclamacaoModel(UUID idReclamacao, String local, String titulo, String descricao, String sugestaoResolucao, StatusReclamacao statusReclamacao, AdminModel adminModel, UsuarioModel usuarioModel) {
        this.idReclamacao = idReclamacao;
        this.local = local;
        this.titulo = titulo;
        this.descricao = descricao;
        this.sugestaoResolucao = sugestaoResolucao;
        this.statusReclamacao = statusReclamacao;
        this.adminModel = adminModel;
        this.usuarioModel = usuarioModel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ReclamacaoModel that)) return false;
        return getIdReclamacao().equals(that.getIdReclamacao());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdReclamacao());
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
