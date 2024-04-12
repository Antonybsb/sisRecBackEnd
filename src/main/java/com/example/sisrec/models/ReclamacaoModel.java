package com.example.sisrec.models;

import com.example.sisrec.domain.enums.StatusReclamacao;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
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
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime dataReclamacao;

    protected void onCreate() {
        dataReclamacao = LocalDateTime.now();
    }


    @ManyToOne
    @JoinColumn(name = "categoria_id_categoria")
    private CategoriaModel categoria;

    @ManyToOne
    @JoinColumn(name = "usuario_id_usuario")
    private UsuarioModel usuario;

    @Enumerated(EnumType.STRING)
    private StatusReclamacao status;


}
