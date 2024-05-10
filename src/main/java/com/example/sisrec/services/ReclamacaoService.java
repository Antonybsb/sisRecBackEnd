package com.example.sisrec.services;

import com.example.sisrec.models.ReclamacaoModel;
import com.example.sisrec.models.UsuarioModel;
import com.example.sisrec.repositories.ReclamacaoRepository;
import com.example.sisrec.repositories.UsuarioRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ReclamacaoService {

    @Autowired
    private ReclamacaoRepository reclamacaoRepository;
    private UsuarioRepository usuarioRepository;


    public ReclamacaoService(ReclamacaoRepository reclamacaoRepository) {
        this.reclamacaoRepository = reclamacaoRepository;
    }

    public ReclamacaoModel criarReclamacao(ReclamacaoModel novaReclamacao, UUID idPessoa) {
        UsuarioModel usuario = usuarioRepository.findById(idPessoa).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        novaReclamacao.setUsuario(usuario);
        return reclamacaoRepository.save(novaReclamacao);
    }

    public List<ReclamacaoModel> buscarTodasReclamacoes() {
        return reclamacaoRepository.findAll();
    }

    public Optional<ReclamacaoModel> buscarReclamacaoPorId(UUID id) {
        return reclamacaoRepository.findById(id);
    }

    public List<ReclamacaoModel> getReclamacoesByUsuarioId(UUID usuarioId) {
        return reclamacaoRepository.findByUsuarioIdPessoa(usuarioId);
    }


    public Optional<ReclamacaoModel> deletarReclamacao(UUID id) {
        reclamacaoRepository.deleteById(id);
        return null;
    }


    public Optional<ReclamacaoModel> atualizarReclamacao(UUID id, ReclamacaoModel reclamacaoAtualizada) {
        Optional<ReclamacaoModel> reclamacaoO = buscarReclamacaoPorId(id);
        if (reclamacaoO.isEmpty()) {
            return Optional.empty();
        }
        ReclamacaoModel reclamacaoExistente = reclamacaoO.get();
        BeanUtils.copyProperties(reclamacaoAtualizada, reclamacaoExistente, "id", "dataAberturaReclamacao");
        return Optional.of(reclamacaoRepository.save(reclamacaoExistente));
    }

}