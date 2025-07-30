package com.example.springwebexample.repository;

import com.example.springwebexample.models.Pessoa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    @Query("SELECT p " +
            "FROM Pessoa p " +
            "WHERE (:nome IS NULL OR UPPER(p.nome) LIKE :nome) " +
            "AND (:cpf IS NULL OR p.cpf = :cpf) ")
    Page<Pessoa> findByFilter(String nome, String cpf, Pageable pageable);
}
