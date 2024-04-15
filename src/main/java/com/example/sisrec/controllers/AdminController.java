package com.example.sisrec.controllers;

import com.example.sisrec.dtos.AdminRecordDto;
import com.example.sisrec.models.AdminModel;
import com.example.sisrec.services.AdminService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/admin")
public class AdminController {

    final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }


    @PostMapping
    public ResponseEntity<AdminModel> saveAdmin(@RequestBody @Valid AdminRecordDto adminRecordDto) {
        var adminModel = new AdminModel();
        BeanUtils.copyProperties(adminRecordDto, adminModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(adminService.save(adminModel));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneAdmin(@PathVariable(value = "id") UUID id){
        Optional<AdminModel> adminO = adminService.findById(id);
        if (adminO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Admin não encontrado.");
        }
        return ResponseEntity.status(HttpStatus.OK).body((adminO.get()));
    }

    @GetMapping
    public ResponseEntity<List<AdminModel>> getAllTecnicos() {
        return ResponseEntity.status(HttpStatus.OK).body(adminService.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateAdmin(@PathVariable(value="id") UUID id,
                                                   @RequestBody @Valid AdminRecordDto adminRecordDto) {
        Optional<AdminModel> adminO = adminService.findById(id);
        if (adminO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Admin não encontrado.");
        }
        var adminModel = adminO.get();
        BeanUtils.copyProperties(adminRecordDto, adminModel);
        //Para não setar o Id e nem a data de abertura quando atualizar
//       reclamacaoModel.setIdReclamacao(reclamacaoO.get().getIdReclamacao());
//       reclamacaoModel.setDataAberturaReclamacao(reclamacaoO.get().getDataAberturaReclamacao());
        return ResponseEntity.status(HttpStatus.OK).body(adminService.save(adminModel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteAdmin(@PathVariable(value="id") UUID id) {
        Optional<AdminModel> adminO = adminService.findById(id);
        if (adminO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Admin não encontrado.");
        }
        adminService.delete(adminO.get());
        return ResponseEntity.status(HttpStatus.OK).body("Admin deletado com sucesso.");
    }




}
