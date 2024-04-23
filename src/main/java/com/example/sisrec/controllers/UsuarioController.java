package com.example.sisrec.controllers;

import com.example.sisrec.models.UsuarioModel;
import com.example.sisrec.services.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }


    @GetMapping
    public ResponseEntity<List<UsuarioModel>> getAllUsuarios() {
        List<UsuarioModel> usuarios = usuarioService.findAll();
        return ResponseEntity.ok(usuarios);
    }
}
