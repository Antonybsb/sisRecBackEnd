package com.example.sisrec.controllers;

import com.example.sisrec.models.ReclamacaoModel;
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




//    @GetMapping
//    public ResponseEntity<List<UsuarioModel>> getAllUsuarios() {
//        List<UsuarioModel> usuarios = usuarioService.findAll();
//        return ResponseEntity.ok(usuarios);
//    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioModel> buscarPorId(@PathVariable UUID id) {
        Optional<UsuarioModel> usuarioO = usuarioService.buscarPorId(id);
        return usuarioO.map(usuario -> ResponseEntity.ok().body(usuario))
                .orElse(ResponseEntity.notFound().build());
    }

    //erro aqui
    @GetMapping
    public ResponseEntity<List<UsuarioModel>> buscarTodos() {
        List<UsuarioModel> usuarios = usuarioService.buscartodos();
        return ResponseEntity.status(HttpStatus.OK).body(usuarios);
    }



    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizar(@PathVariable UUID id, @RequestBody UsuarioModel usuario) {
        Optional<UsuarioModel> usuarioO = usuarioService.buscarPorId(id);
        if (usuarioO.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        usuario.setIdPessoa(id);
        usuarioService.atualizar(usuario);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletar(@PathVariable UUID id) {
        Optional<UsuarioModel> usuarioO = usuarioService.buscarPorId(id);
        if (usuarioO.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        usuarioService.deletar(id);
        return ResponseEntity.ok().build();
    }


}