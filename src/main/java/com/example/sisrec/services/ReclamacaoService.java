package com.example.sisrec.services;

import com.example.sisrec.models.ReclamacaoModel;
import com.example.sisrec.repositories.ReclamacaoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ReclamacaoService {

    final ReclamacaoRepository reclamacaoRepository;

    public ReclamacaoService(ReclamacaoRepository reclamacaoRepository) {
        this.reclamacaoRepository = reclamacaoRepository;
    }

    @Transactional
    public ReclamacaoModel save(ReclamacaoModel reclamacaoModel) {
        return reclamacaoRepository.save(reclamacaoModel);
    }

    public List<ReclamacaoModel> findAll() {
        return reclamacaoRepository.findAll();
    }

    public Optional<ReclamacaoModel> findById(UUID id) {
        return reclamacaoRepository.findById(id);
    }

    @Transactional
    public void delete(ReclamacaoModel reclamacaoModel) {
        reclamacaoRepository.delete(reclamacaoModel);
    }
}
