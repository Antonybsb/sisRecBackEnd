package com.example.sisrec.dtos;

import com.example.sisrec.enums.UserRole;

public record RegistroDTO(String nome, String password, String cpf, String email) {
}
