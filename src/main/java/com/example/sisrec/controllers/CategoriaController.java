package com.example.sisrec.controllers;

import com.example.sisrec.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoriaController {

    @Autowired
    CategoriaRepository categoriaRepository;
}
