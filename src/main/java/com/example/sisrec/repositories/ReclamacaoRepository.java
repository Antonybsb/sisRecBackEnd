package com.example.sisrec.repositories;

import com.example.sisrec.models.ReclamacaoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ReclamacaoRepository extends JpaRepository<ReclamacaoModel, UUID> {
}
