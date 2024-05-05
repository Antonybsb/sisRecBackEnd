package com.example.sisrec.controllers;

import com.example.sisrec.services.TokenService;
import com.example.sisrec.dtos.AuthenticationRecordDTO;
import com.example.sisrec.dtos.NomeResponseDTO;
import com.example.sisrec.dtos.RegistroDTO;
import com.example.sisrec.models.UsuarioModel;
import com.example.sisrec.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository repository;

    @Autowired
    private TokenService tokenService;


    @PostMapping("/login")
    ResponseEntity login(@RequestBody @Valid AuthenticationRecordDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((UsuarioModel) auth.getPrincipal());

        return ResponseEntity.ok(new NomeResponseDTO(token));
    }

    @PostMapping("/registro")
    public ResponseEntity registro(@RequestBody @Valid RegistroDTO data) {
        if (this.repository.findByEmail(data.nome()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        LocalDate today = LocalDate.now();
        UsuarioModel newUser = new UsuarioModel(
                data.nome(),
                data.cpf(),
                data.email(),
                encryptedPassword);

        this.repository.save(newUser);
        return ResponseEntity.ok().build();


    }


}
