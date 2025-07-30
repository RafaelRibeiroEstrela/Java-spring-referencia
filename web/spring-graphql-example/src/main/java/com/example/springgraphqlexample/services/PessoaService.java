package com.example.springgraphqlexample.services;

import com.example.springgraphqlexample.dto.PessoaRequest;
import com.example.springgraphqlexample.dto.PessoaResponse;
import com.example.springgraphqlexample.exceptions.ResourceNotFoundException;
import com.example.springgraphqlexample.models.Pessoa;
import com.example.springgraphqlexample.repository.PessoaRepository;
import org.springframework.stereotype.Service;

@Service
public class PessoaService {

    private final PessoaRepository pessoaRepository;

    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    public PessoaResponse create(PessoaRequest request) {
        Pessoa model = request.toModel();
        model = pessoaRepository.save(model);
        return new PessoaResponse(model);
    }

    public PessoaResponse update(PessoaRequest request, long id) {
        Pessoa model = request.toModel();
        model.setId(id);
        model = pessoaRepository.save(model);
        return new PessoaResponse(model);
    }

    public void delete(long id) {
        pessoaRepository.deleteById(id);
    }

    public PessoaResponse findById(long id) {
        return pessoaRepository.findById(id).map(PessoaResponse::new).orElseThrow(() -> new ResourceNotFoundException("Nenhuma pessoa encontrada"));
    }


}
