package com.example.sisrec.controllers;

import com.example.sisrec.dtos.ReclamacaoRecordDto;
import com.example.sisrec.models.ReclamacaoModel;
import com.example.sisrec.services.ReclamacaoService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/reclamacoes")
public class ReclamacaoController {

    final ReclamacaoService reclamacaoService;

    public ReclamacaoController(ReclamacaoService reclamacaoService) {
        this.reclamacaoService = reclamacaoService;
    }


        @PostMapping
    public ResponseEntity<ReclamacaoModel> saveReclamacao(@RequestBody @Valid ReclamacaoRecordDto reclamacaoRecordDto) {
        var reclamacaoModel = new ReclamacaoModel();
        BeanUtils.copyProperties(reclamacaoRecordDto, reclamacaoModel);
        reclamacaoModel.setDataAberturaReclamacao(LocalDate.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(reclamacaoService.save(reclamacaoModel));
    }

    @GetMapping
    public ResponseEntity<List<ReclamacaoModel>> getAllReclamacoes() {
        return ResponseEntity.status(HttpStatus.OK).body(reclamacaoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneReclamacao(@PathVariable(value = "id") UUID id){
        Optional<ReclamacaoModel> reclamacaoO = reclamacaoService.findById(id);
        if (reclamacaoO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Reclamação não encontrada.");
        }
        return ResponseEntity.status(HttpStatus.OK).body((reclamacaoO.get()));
    }

        @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteReclamacao(@PathVariable(value="id") UUID id) {
        Optional<ReclamacaoModel> reclamacaoO = reclamacaoService.findById(id);
        if (reclamacaoO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Reclamação não encontrada.");
        }
        reclamacaoService.delete(reclamacaoO.get());
        return ResponseEntity.status(HttpStatus.OK).body("Reclamação deletada com sucesso.");
    }

        @PutMapping("/{id}")
    public ResponseEntity<Object> updateReclamacao(@PathVariable(value="id") UUID id,
        @RequestBody @Valid ReclamacaoRecordDto reclamacaoRecordDto) {
        Optional<ReclamacaoModel> reclamacaoO = reclamacaoService.findById(id);
        if (reclamacaoO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Reclamação não encontrada.");
        }
        var reclamacaoModel = reclamacaoO.get();
        BeanUtils.copyProperties(reclamacaoRecordDto, reclamacaoModel);
        //Para não setar o Id e nem a data de abertura quando atualizar
//       reclamacaoModel.setIdReclamacao(reclamacaoO.get().getIdReclamacao());
//       reclamacaoModel.setDataAberturaReclamacao(reclamacaoO.get().getDataAberturaReclamacao());
        return ResponseEntity.status(HttpStatus.OK).body(reclamacaoService.save(reclamacaoModel));
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


}
