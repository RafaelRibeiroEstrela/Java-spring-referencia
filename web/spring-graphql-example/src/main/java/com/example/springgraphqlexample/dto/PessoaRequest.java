package com.example.springgraphqlexample.dto;

import com.example.springgraphqlexample.models.Pessoa;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class PessoaRequest {

    private String nome;
    @JsonFormat(pattern = "dd/MM/yyyyy")
    private LocalDate dataNascimento;
    private String cpf;

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

    public Pessoa toModel() {
        Pessoa model = new Pessoa();
        model.setNome(this.nome);
        model.setDataNascimento(this.dataNascimento);
        model.setCpf(this.cpf);
        return model;
    }
}
