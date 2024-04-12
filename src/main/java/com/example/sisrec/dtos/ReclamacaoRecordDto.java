package com.example.sisrec.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ReclamacaoRecordDto(@NotBlank String descricao, String localizacao, LocalDateTime dataReclamacao) {
}
