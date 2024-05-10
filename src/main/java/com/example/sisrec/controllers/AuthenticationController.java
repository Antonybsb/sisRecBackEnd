
package com.example.sisrec.controllers;

import com.example.sisrec.configs.security.AuthenticationResponse;
import com.example.sisrec.services.TokenService;
import com.example.sisrec.dtos.AuthenticationRecordDTO;
import com.example.sisrec.dtos.RegistroDTO;
import com.example.sisrec.models.UsuarioModel;
import com.example.sisrec.repositories.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private TokenService tokenService;


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationRecordDTO data) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(data.email(), data.password())
            );
            UsuarioModel user = (UsuarioModel) authentication.getPrincipal();
            String newToken = tokenService.generateToken(user);
            return ResponseEntity.ok(new AuthenticationResponse(newToken));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed: " + e.getMessage());
        }
    }

    @PostMapping("/registro")
    public ResponseEntity registro(@RequestBody @Valid RegistroDTO data) {
        if (this.repository.findByEmail(data.email()).isPresent()) {
            return ResponseEntity.badRequest().body("Erro: O email já está em uso.");
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        LocalDate today = LocalDate.now();
        UsuarioModel newUser = new UsuarioModel(
                data.nome(),
                data.cpf(),
                data.email(),
                encryptedPassword);

        this.repository.save(newUser);
        return ResponseEntity.ok("Usuário registrado com sucesso!");


    }


}
