package com.example.sisrec.services;

import com.example.sisrec.repositories.AdminRepository;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    final AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }
}
