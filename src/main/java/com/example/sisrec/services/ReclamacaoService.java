package com.example.sisrec.services;

import com.example.sisrec.repositories.ReclamacaoRepository;
import org.springframework.stereotype.Service;

@Service
public class ReclamacaoService {

    final ReclamacaoRepository reclamacaoRepository;

    public ReclamacaoService(ReclamacaoRepository reclamacaoRepository) {
        this.reclamacaoRepository = reclamacaoRepository;
    }
}
