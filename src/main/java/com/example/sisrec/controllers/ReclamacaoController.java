package com.example.sisrec.controllers;

import com.example.sisrec.dtos.ReclamacaoRecordDto;
import com.example.sisrec.models.ReclamacaoModel;
import com.example.sisrec.repositories.ReclamacaoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class ReclamacaoController {

    @Autowired
    ReclamacaoRepository reclamacaoRepository;

    @PostMapping("/reclamacoes")
    public ResponseEntity<ReclamacaoModel> saveReclamacao(@RequestBody @Valid ReclamacaoRecordDto reclamacaoRecordDto) {
        var reclamacaoModel = new ReclamacaoModel();
        BeanUtils.copyProperties(reclamacaoRecordDto, reclamacaoModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(reclamacaoRepository.save(reclamacaoModel));
    }

    @GetMapping("/reclamacoes")
    public ResponseEntity<List<ReclamacaoModel>> getAllReclamacoes() {
        return ResponseEntity.status(HttpStatus.OK).body(reclamacaoRepository.findAll());
    }

    @GetMapping("/reclamacoes/{id}")
    public ResponseEntity<Object> getOneReclamacao(@PathVariable(value = "id") UUID id){
        Optional<ReclamacaoModel> reclamacaoO = reclamacaoRepository.findById(id);
        if (reclamacaoO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Reclamação não encontrada.");
        }
        return ResponseEntity.status(HttpStatus.OK).body((reclamacaoO.get()));
    }

    @PutMapping("/reclamacoes/{id}")
    public ResponseEntity<Object> updateReclamacao(@PathVariable(value="id") UUID id,
        @RequestBody @Valid ReclamacaoRecordDto reclamacaoRecordDto) {
        Optional<ReclamacaoModel> reclamacaoO = reclamacaoRepository.findById(id);
        if (reclamacaoO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Reclamação não encontrada.");
        }
        var reclamacaoModel = reclamacaoO.get();
        BeanUtils.copyProperties(reclamacaoRecordDto, reclamacaoModel);
        return ResponseEntity.status(HttpStatus.OK).body(reclamacaoRepository.save(reclamacaoModel));
    }

    @DeleteMapping("/reclamacoes/{id}")
    public ResponseEntity<Object> updateReclamacao(@PathVariable(value="id") UUID id) {
        Optional<ReclamacaoModel> reclamacaoO = reclamacaoRepository.findById(id);
        if (reclamacaoO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Reclamação não encontrada.");
        }
        reclamacaoRepository.delete(reclamacaoO.get());
        return ResponseEntity.status(HttpStatus.OK).body("Reclamação deletada com sucesso.");
    }


}
