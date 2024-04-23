package com.example.sisrec.dtos;

import com.example.sisrec.enums.UserRole;

import java.time.LocalDate;

public record RegistroDTO(String login, String password, UserRole role, String cpf, String email) {
}
