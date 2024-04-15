package com.example.sisrec.dtos;

import com.example.sisrec.enums.StatusReclamacao;
import com.example.sisrec.models.AdminModel;
import com.example.sisrec.models.UsuarioModel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

public record ReclamacaoRecordDto(@NotBlank String local, @NotBlank String titulo, @NotBlank String descricao, @NotNull String sugestaoResolucao, StatusReclamacao statusReclamacao, AdminModel adminModel, UsuarioModel usuarioModel) {
}
