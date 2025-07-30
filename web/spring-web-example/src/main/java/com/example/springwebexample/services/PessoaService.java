package com.example.springwebexample.services;

import com.example.springwebexample.dto.PessoaFilter;
import com.example.springwebexample.dto.PessoaRequest;
import com.example.springwebexample.dto.PessoaResponse;
import com.example.springwebexample.exceptions.ResourceNotFoundException;
import com.example.springwebexample.models.Pessoa;
import com.example.springwebexample.repository.PessoaRepository;
import com.example.springwebexample.utils.CpfUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Page<PessoaResponse> findAll(PessoaFilter filter, Pageable pageable) {
        String nome = filter.getNome() == null || filter.getNome().trim().isEmpty() ? null : "%" + filter.getNome().toUpperCase() + "%";
        String cpf = filter.getCpf() == null || filter.getCpf().trim().isEmpty() ? null : CpfUtil.removerMascaraCpf(filter.getCpf());
        return pessoaRepository.findByFilter(nome, cpf, pageable).map(PessoaResponse::new);
    }


}
