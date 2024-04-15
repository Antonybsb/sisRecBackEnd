package com.example.sisrec.services;

import com.example.sisrec.models.AdminModel;
import com.example.sisrec.models.UsuarioModel;
import com.example.sisrec.repositories.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UsuarioService {

    final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public UsuarioModel save(UsuarioModel usuarioModel) {
        return usuarioRepository.save(usuarioModel);
    }

    public Optional<UsuarioModel> findById(UUID id) {
        return usuarioRepository.findById(id);
    }

    public List<UsuarioModel> findAll() {
        return usuarioRepository.findAll();
    }


    public void delete(UsuarioModel usuarioModel) {
        usuarioRepository.delete(usuarioModel);
    }
}
