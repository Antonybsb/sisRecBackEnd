package com.example.sisrec.services;

import com.example.sisrec.dtos.AdminRecordDto;
import com.example.sisrec.models.AdminModel;
import com.example.sisrec.models.ReclamacaoModel;
import com.example.sisrec.repositories.AdminRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AdminService {

    final AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }


    public Optional<AdminModel> findById(UUID id) {
        return adminRepository.findById(id);
    }

    public AdminModel save(AdminModel adminModel) {
        return adminRepository.save(adminModel);
    }


    public List<AdminModel> findAll() {
        return adminRepository.findAll();
    }

    @Transactional
    public void delete(AdminModel adminModel) {
        adminRepository.delete(adminModel);
    }
}

