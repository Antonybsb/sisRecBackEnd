package com.example.sisrec.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UsuarioRecordDto(@NotBlank String nome, @NotNull String cpf, @NotBlank String email, @NotBlank String senha) {
}