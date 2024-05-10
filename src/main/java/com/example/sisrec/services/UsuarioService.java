package com.example.sisrec.services;

import com.example.sisrec.models.UsuarioModel;
import com.example.sisrec.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

//    public List<UsuarioModel> findAll() {
//        return usuarioRepository.findAll();
//    }


    public Optional<UsuarioModel> buscarPorId(UUID id) {
        return usuarioRepository.findById(id);
    }

    public List<UsuarioModel> buscartodos() {
        return usuarioRepository.findAll();
    }

    public void atualizar(UsuarioModel usuario) {
        usuarioRepository.save(usuario);
    }

    public void deletar(UUID id) {
        usuarioRepository.deleteById(id);
    }
}