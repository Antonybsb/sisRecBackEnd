package com.example.sisrec.controllers;

import com.example.sisrec.models.ReclamacaoModel;
import com.example.sisrec.models.UsuarioModel;
import com.example.sisrec.repositories.ReclamacaoRepository;
import com.example.sisrec.repositories.UsuarioRepository;
import com.example.sisrec.services.ReclamacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController()
@RequestMapping("/reclamacoes")
public class ReclamacaoController {


    private  final ReclamacaoService reclamacaoService;

    public ReclamacaoController(ReclamacaoService reclamacaoService) {
        this.reclamacaoService = reclamacaoService;
    }

    @Autowired
    private ReclamacaoRepository reclamacaoRepository;

    @Autowired
    private UsuarioRepository userRepository;

    @PostMapping
    public ResponseEntity<ReclamacaoModel> criarReclamacao(@RequestBody ReclamacaoModel novaReclamacao, @AuthenticationPrincipal UsuarioModel usuario) {
        novaReclamacao.setUsuario(usuario);

        ReclamacaoModel reclamacaoSalva = reclamacaoRepository.save(novaReclamacao);

        return ResponseEntity.status(HttpStatus.CREATED).body(reclamacaoSalva);
    }


    @GetMapping
    public ResponseEntity<List<ReclamacaoModel>> buscarTodasReclamacoes() {
        List<ReclamacaoModel> reclamacoes = reclamacaoService.buscarTodasReclamacoes();
            return ResponseEntity.status(HttpStatus.OK).body(reclamacoes);
        }



    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneReclamacao(@PathVariable(value = "id") UUID id) {
        Optional<ReclamacaoModel> reclamacaoO = reclamacaoService.buscarReclamacaoPorId(id);
        if (reclamacaoO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Reclamação não encontrada.");
        }
        return ResponseEntity.status(HttpStatus.OK).body((reclamacaoO.get()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletarReclamacao(@PathVariable(value = "id") UUID id) {
        Optional<ReclamacaoModel> reclamacaoO = reclamacaoService.deletarReclamacao(id);
        if (reclamacaoO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Reclamação não encontrada.");
        }
        reclamacaoService.deletarReclamacao(id);
        return ResponseEntity.status(HttpStatus.OK).body("Reclamação deletada com sucesso.");
    }



//    @PutMapping("/{id}")
//    public ResponseEntity<Object> atualizarReclamacao(@PathVariable(value = "id") UUID id,
//                                                      @RequestBody @Valid ReclamacaoRecordDto reclamacaoRecordDto) {
//        Optional<ReclamacaoModel> reclamacaoO = reclamacaoService.atualizarReclamacao(id);
//        if (reclamacaoO.isEmpty()) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Reclamação não encontrada.");
//        }
//        var reclamacaoModel = reclamacaoO.get();
//        BeanUtils.copyProperties(reclamacaoRecordDto, reclamacaoModel);
//        //Para não setar o Id e nem a data de abertura quando atualizar
////       reclamacaoModel.setIdReclamacao(reclamacaoO.get().getIdReclamacao());
////       reclamacaoModel.setDataAberturaReclamacao(reclamacaoO.get().getDataAberturaReclamacao());
//        return ResponseEntity.status(HttpStatus.OK).body(reclamacaoService.save(reclamacaoModel));
//    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizarReclamacao(@PathVariable UUID id, @RequestBody ReclamacaoModel reclamacao) {
        Optional<ReclamacaoModel> atualizada = reclamacaoService.atualizarReclamacao(id, reclamacao);
        if (atualizada.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }




}






    //    @Autowired
//    ReclamacaoRepository reclamacaoRepository;
//
//    @PostMapping("/reclamacoes")
//    public ResponseEntity<ReclamacaoModel> saveReclamacao(@RequestBody @Valid ReclamacaoRecordDto reclamacaoRecordDto) {
//        var reclamacaoModel = new ReclamacaoModel();
//        BeanUtils.copyProperties(reclamacaoRecordDto, reclamacaoModel);
//        return ResponseEntity.status(HttpStatus.CREATED).body(reclamacaoRepository.save(reclamacaoModel));
//    }
//
//    @GetMapping("/reclamacoes")
//    public ResponseEntity<List<ReclamacaoModel>> getAllReclamacoes() {
//        return ResponseEntity.status(HttpStatus.OK).body(reclamacaoRepository.findAll());
//    }
//
//    @GetMapping("/reclamacoes/{id}")
//    public ResponseEntity<Object> getOneReclamacao(@PathVariable(value = "id") UUID id){
//        Optional<ReclamacaoModel> reclamacaoO = reclamacaoRepository.findById(id);
//        if (reclamacaoO.isEmpty()) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Reclamação não encontrada.");
//        }
//        return ResponseEntity.status(HttpStatus.OK).body((reclamacaoO.get()));
//    }
//
//    @PutMapping("/reclamacoes/{id}")
//    public ResponseEntity<Object> updateReclamacao(@PathVariable(value="id") UUID id,
//        @RequestBody @Valid ReclamacaoRecordDto reclamacaoRecordDto) {
//        Optional<ReclamacaoModel> reclamacaoO = reclamacaoRepository.findById(id);
//        if (reclamacaoO.isEmpty()) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Reclamação não encontrada.");
//        }
//        var reclamacaoModel = reclamacaoO.get();
//        BeanUtils.copyProperties(reclamacaoRecordDto, reclamacaoModel);
//        return ResponseEntity.status(HttpStatus.OK).body(reclamacaoRepository.save(reclamacaoModel));
//    }
//
//    @DeleteMapping("/reclamacoes/{id}")
//    public ResponseEntity<Object> updateReclamacao(@PathVariable(value="id") UUID id) {
//        Optional<ReclamacaoModel> reclamacaoO = reclamacaoRepository.findById(id);
//        if (reclamacaoO.isEmpty()) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Reclamação não encontrada.");
//        }
//        reclamacaoRepository.delete(reclamacaoO.get());
//        return ResponseEntity.status(HttpStatus.OK).body("Reclamação deletada com sucesso.");
//    }


