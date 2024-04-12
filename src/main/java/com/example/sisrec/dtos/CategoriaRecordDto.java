package com.example.sisrec.dtos;

import jakarta.validation.constraints.NotBlank;

public record CategoriaRecordDto(@NotBlank String nome, @NotBlank String descricao) {
}
