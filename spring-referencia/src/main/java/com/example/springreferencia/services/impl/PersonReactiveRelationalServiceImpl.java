package com.example.springreferencia.services.impl;

import com.example.springreferencia.dtos.PersonDTO;
import com.example.springreferencia.repositories.PersonReactiveRelationalRepository;
import com.example.springreferencia.services.PersonReactiveService;
import com.example.springreferencia.services.exceptions.InvalidRuleException;
import com.example.springreferencia.services.exceptions.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PersonReactiveRelationalServiceImpl implements PersonReactiveService {

    private final PersonReactiveRelationalRepository repository;
    private static final Logger LOGGER = LoggerFactory.getLogger(PersonReactiveRelationalServiceImpl.class);

    public PersonReactiveRelationalServiceImpl(PersonReactiveRelationalRepository repository) {
        this.repository = repository;
    }

    @Override
    public Flux<PersonDTO> findAll() {
        LOGGER.info("Buscando todos os recursos no Mongodb de forma reativa");
        return repository.findAll()
                .map(PersonDTO::new)
                .switchIfEmpty(Mono.error(() -> {
                    LOGGER.error("Recurso não encontrado.");
                    return new ResourceNotFoundException("Recurso não encontrado.");
                }));
    }

    @Override
    public Mono<PersonDTO> findById(String id) {
        LOGGER.info("Buscando recurso no Mongodb de forma reativa com chave = {}", id);
        try {
            return repository.findById(Long.parseLong(id))
                    .map(PersonDTO::new)
                    .switchIfEmpty(Mono.error(() -> {
                        LOGGER.error("Não foi encontrado um recurso com a chave = {}", id);
                        return new ResourceNotFoundException("Não foi encontrado um recurso com a chave = " + id);
                    }));
        } catch (IllegalArgumentException e) {
            LOGGER.error("Erro: {}", e.getMessage());
            throw new InvalidRuleException(e.getMessage());
        }
    }

    @Override
    public Flux<PersonDTO> findByCompany(Integer company) {
        return repository.findByCompany(company).map(PersonDTO::new);
    }
}
