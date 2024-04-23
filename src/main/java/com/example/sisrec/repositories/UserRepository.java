package com.example.sisrec.repositories;

import com.example.sisrec.models.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.UUID;

public interface UserRepository extends JpaRepository<UsuarioModel, UUID> {
    UserDetails findByLogin(String login);
}
