package com.example.sisrec.controllers;

import com.example.sisrec.dtos.AdminRecordDto;
import com.example.sisrec.dtos.UsuarioRecordDto;
import com.example.sisrec.models.AdminModel;
import com.example.sisrec.models.UsuarioModel;
import com.example.sisrec.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<UsuarioModel> saveUsuario(@RequestBody @Valid UsuarioRecordDto usuarioRecordDto) {
        var usuarioModel = new UsuarioModel();
        BeanUtils.copyProperties(usuarioRecordDto, usuarioModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.save(usuarioModel));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneUsuario(@PathVariable(value = "id") UUID id){
        Optional<UsuarioModel> usuarioO = usuarioService.findById(id);
        if (usuarioO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado.");
        }
        return ResponseEntity.status(HttpStatus.OK).body((usuarioO.get()));
    }

    @GetMapping
    public ResponseEntity<List<UsuarioModel>> getAllUsuarios() {
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateUsuario(@PathVariable(value="id") UUID id,
                                              @RequestBody @Valid UsuarioRecordDto usuarioRecordDto) {
        Optional<UsuarioModel> usuarioO = usuarioService.findById(id);
        if (usuarioO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado.");
        }
        var usuarioModel = usuarioO.get();
        BeanUtils.copyProperties(usuarioRecordDto, usuarioModel);
        //Para não setar o Id e nem a data de abertura quando atualizar
//       reclamacaoModel.setIdReclamacao(reclamacaoO.get().getIdReclamacao());
//       reclamacaoModel.setDataAberturaReclamacao(reclamacaoO.get().getDataAberturaReclamacao());
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.save(usuarioModel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUsuario(@PathVariable(value="id") UUID id) {
        Optional<UsuarioModel> usuarioO = usuarioService.findById(id);
        if (usuarioO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado.");
        }
        usuarioService.delete(usuarioO.get());
        return ResponseEntity.status(HttpStatus.OK).body("Usuario deletado com sucesso.");
    }

}
