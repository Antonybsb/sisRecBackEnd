package com.example.sisrec.models;

import com.example.sisrec.enums.StatusReclamacao;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "TB_RECLAMACAO")
@Getter
@Setter
@NoArgsConstructor
public class ReclamacaoModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idReclamacao;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataAberturaReclamacao = LocalDate.now();

    @Transient
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataEncerramentoReclamacao;
    private String local;
    private String titulo;
    private String descricao;
    private String sugestaoResolucao;

    @Transient
    @Enumerated(EnumType.ORDINAL)
    private StatusReclamacao statusReclamacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    @JsonIgnore
    private UsuarioModel usuario;


    public ReclamacaoModel(UUID idReclamacao, String local, String titulo, String descricao, String sugestaoResolucao, StatusReclamacao statusReclamacao,  UsuarioModel usuarioModel) {
        this.idReclamacao = idReclamacao;
        this.local = local;
        this.titulo = titulo;
        this.descricao = descricao;
        this.sugestaoResolucao = sugestaoResolucao;
        this.statusReclamacao = statusReclamacao;

        this.usuario = usuarioModel;
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


}
