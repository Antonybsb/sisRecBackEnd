package com.example.sisrec.controllers;

import com.example.sisrec.models.UsuarioModel;
import com.example.sisrec.services.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public ResponseEntity<List<UsuarioModel>> buscarTodos() {
        List<UsuarioModel> usuarios = usuarioService.buscartodos();
        return ResponseEntity.status(HttpStatus.OK).body(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioModel> buscarPorId(@PathVariable UUID id) {
        Optional<UsuarioModel> usuarioO = usuarioService.buscarUsuarioPorId(id);
        return usuarioO.map(usuario -> ResponseEntity.ok().body(usuario))
                .orElse(ResponseEntity.notFound().build());
    }



    @PutMapping("/{id}")
    public ResponseEntity<UsuarioModel> atualizarUsuario(@PathVariable UUID id, @RequestBody UsuarioModel usuarioModel) {
        return usuarioService.atualizarUsuario(id, usuarioModel)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarUsuario(@PathVariable UUID id) {
        Optional<UsuarioModel> usuarioDeletado = usuarioService.deletarUsuario(id);
        if (usuarioDeletado.isPresent()) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }




}