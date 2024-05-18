package com.example.sisrec.services;

import com.example.sisrec.models.UsuarioModel;
import com.example.sisrec.repositories.UsuarioRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UsuarioService {


    private final UsuarioRepository usuarioRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<UsuarioModel> buscartodos() {
        return usuarioRepository.findAll();
    }

    public Optional<UsuarioModel> buscarUsuarioPorId(UUID id) {
        return usuarioRepository.findById(id);
    }

    public Optional<UsuarioModel> atualizarUsuario(UUID id, UsuarioModel updatedUser) {
        return usuarioRepository.findById(id).map(existingUser -> {
            existingUser.update(updatedUser, passwordEncoder);
            return usuarioRepository.save(existingUser);
        });
    }

//    public Optional<UsuarioModel> atualizarUsuario(UUID id, UsuarioModel updatedUser) {
//        return usuarioRepository.findById(id).map(existingUser -> {
//            existingUser.update(updatedUser);
//            return usuarioRepository.save(existingUser);
//        });
//    }

    public Optional<UsuarioModel> deletarUsuario(UUID id) {
        Optional<UsuarioModel> usuarioExistente = usuarioRepository.findById(id);
        if (usuarioExistente.isPresent()) {
            usuarioRepository.deleteById(id);
            return usuarioExistente;
        } else {
            return Optional.empty();
        }
    }

}