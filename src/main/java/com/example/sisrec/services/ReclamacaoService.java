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
        Optional<ReclamacaoModel> reclamacaoExistente = reclamacaoRepository.findById(id);
        if (reclamacaoExistente.isPresent()) {
            reclamacaoRepository.deleteById(id);
            return reclamacaoExistente;
        } else {
            return Optional.empty();
        }
    }

    public Optional<ReclamacaoModel> atualizarReclamacao(UUID id, ReclamacaoModel reclamacaoAtualizada) {
        Optional<ReclamacaoModel> reclamacaoExistente = reclamacaoRepository.findById(id);

        if (reclamacaoExistente.isPresent()) {
            ReclamacaoModel reclamacao = reclamacaoExistente.get();
            // Atualiza os campos da reclamação existente com base na reclamação atualizada
            // Certifique-se de que apenas os campos necessários estejam sendo atualizados
            reclamacao.setTitulo(reclamacaoAtualizada.getTitulo());
            reclamacao.setDescricao(reclamacaoAtualizada.getDescricao());
            reclamacao.setLocal(reclamacaoAtualizada.getLocal());
            reclamacao.setSugestaoResolucao(reclamacaoAtualizada.getSugestaoResolucao());
            reclamacao.setStatusReclamacao(reclamacaoAtualizada.getStatusReclamacao());

            // Salva a reclamação atualizada no repositório
            return Optional.of(reclamacaoRepository.save(reclamacao));
        } else {
            return Optional.empty();
        }
    }


//    public Optional<ReclamacaoModel> atualizarReclamacao(UUID id, ReclamacaoModel reclamacaoAtualizada) {
//        Optional<ReclamacaoModel> reclamacaoO = buscarReclamacaoPorId(id);
//        if (reclamacaoO.isEmpty()) {
//            return Optional.empty();
//        }
//        ReclamacaoModel reclamacaoExistente = reclamacaoO.get();
//        BeanUtils.copyProperties(reclamacaoAtualizada, reclamacaoExistente, "id", "dataAberturaReclamacao");
//        // Mantém o nomeUsuario existente
//        reclamacaoExistente.setNomeUsuario(reclamacaoExistente.getNomeUsuario());
//        return Optional.of(reclamacaoRepository.save(reclamacaoExistente));
//    }

}