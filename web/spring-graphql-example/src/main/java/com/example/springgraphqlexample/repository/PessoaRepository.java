package com.example.springgraphqlexample.repository;

import com.example.springgraphqlexample.models.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
}
