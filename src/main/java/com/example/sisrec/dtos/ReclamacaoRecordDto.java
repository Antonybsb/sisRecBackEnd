package com.example.sisrec.dtos;

import com.example.sisrec.enums.StatusReclamacao;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ReclamacaoRecordDto(@NotBlank String local, @NotBlank String titulo, @NotBlank String descricao, @NotNull String sugestaoResolucao, StatusReclamacao statusReclamacao) {
}
