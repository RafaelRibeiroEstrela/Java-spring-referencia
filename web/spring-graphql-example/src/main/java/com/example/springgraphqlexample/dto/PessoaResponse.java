package com.example.springgraphqlexample.dto;

import com.example.springgraphqlexample.models.Pessoa;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class PessoaResponse {

    private Long id;
    private String nome;
    @JsonFormat(pattern = "dd/MM/yyyyy")
    private LocalDate dataNascimento;
    private String cpf;

    public PessoaResponse() {}

    public PessoaResponse(Pessoa model) {
        this.id = model.getId();
        this.nome = model.getNome();
        this.dataNascimento = model.getDataNascimento();
        this.cpf = model.getCpf();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
