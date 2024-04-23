package com.example.sisrec.services;

import com.example.sisrec.models.UsuarioModel;
import com.example.sisrec.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<UsuarioModel> findAll() {
        return usuarioRepository.findAll();
    }

}
