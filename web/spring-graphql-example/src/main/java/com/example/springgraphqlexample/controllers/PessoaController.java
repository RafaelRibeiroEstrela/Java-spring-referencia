package com.example.springgraphqlexample.controllers;

import com.example.springgraphqlexample.dto.PessoaRequest;
import com.example.springgraphqlexample.dto.PessoaResponse;
import com.example.springgraphqlexample.services.PessoaService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

@Controller
public class PessoaController {

    private final PessoaService pessoaService;

    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @Transactional
    @QueryMapping
    public ResponseEntity<PessoaResponse> createPessoa(@Argument PessoaRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pessoaService.create(request));
    }

    @Transactional
    @QueryMapping
    public ResponseEntity<PessoaResponse> update(@Argument PessoaRequest request, @Argument Long id) {
        return ResponseEntity.ok().body(pessoaService.update(request, id));
    }

    @Transactional
    @QueryMapping
    public ResponseEntity<PessoaResponse> delete(@Argument Long id) {
        pessoaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Transactional(readOnly = true)
    @QueryMapping
    public ResponseEntity<PessoaResponse> findPessoaById(@Argument Long id) {
        return ResponseEntity.ok().body(pessoaService.findById(id));
    }

    //PAGINADO DA MUITO TRABALHO
}
