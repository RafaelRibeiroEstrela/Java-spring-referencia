package com.example.springreferencia.services.impl;

import com.example.springreferencia.dtos.PersonDTO;
import com.example.springreferencia.repositories.PersonReactiveMongoDBRepository;
import com.example.springreferencia.services.PersonReactiveMongoDBService;
import com.example.springreferencia.services.exceptions.InvalidRuleException;
import com.example.springreferencia.services.exceptions.ResourceNotFoundException;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PersonReactiveMongoDBServiceImpl implements PersonReactiveMongoDBService {

    private final PersonReactiveMongoDBRepository repository;
    private static final Logger LOGGER = LoggerFactory.getLogger(PersonReactiveMongoDBServiceImpl.class);

    public PersonReactiveMongoDBServiceImpl(PersonReactiveMongoDBRepository repository) {
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
            ObjectId objectId = new ObjectId(id);
            return repository.findById(objectId)
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
}
