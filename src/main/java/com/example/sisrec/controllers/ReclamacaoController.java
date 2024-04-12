package com.example.sisrec.controllers;

import com.example.sisrec.dtos.ReclamacaoRecordDto;
import com.example.sisrec.models.ReclamacaoModel;
import com.example.sisrec.repositories.ReclamacaoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
}
