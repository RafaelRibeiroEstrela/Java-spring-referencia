package com.example.springdatareactivemongodb.services.impl;

import com.example.springdatareactivemongodb.dtos.StateDTO;
import com.example.springdatareactivemongodb.models.State;
import com.example.springdatareactivemongodb.repositories.StateRepository;
import com.example.springdatareactivemongodb.services.StateService;
import com.example.springdatareactivemongodb.services.exceptions.DatabaseException;
import com.example.springdatareactivemongodb.services.exceptions.ResourceNotFoundException;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class StateServiceImpl implements StateService {

    private final StateRepository repository;

    public StateServiceImpl(StateRepository repository) {
        this.repository = repository;
    }


    @Override
    public Flux<StateDTO> findAll() {
        return repository.findAll()
                .map(StateDTO::new)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Estado não encontrado.")));
    }

    @Override
    public Mono<StateDTO> findById(String id) {
        try {
            return repository.findById(new ObjectId(id))
                    .map(StateDTO::new)
                    .switchIfEmpty(Mono.error(new ResourceNotFoundException("Estado não encontrado.")));
        } catch (IllegalArgumentException e) {
            throw new DatabaseException("Erro ao converter chave para ObjectId");
        }

    }

    @Override
    public Mono<StateDTO> findByUf(String uf) {
        return repository.findByUf(uf)
                .map(StateDTO::new)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Estado não encontrado.")));
    }

    @Override
    public void copyDTOToEntity(State state, StateDTO stateDTO) {
        try {
            state.setId(new ObjectId(stateDTO.getId()));
            state.setName(stateDTO.getName());
            state.setUf(stateDTO.getUf());
        } catch (IllegalArgumentException e) {
            throw new DatabaseException("Erro ao converter chave para ObjectId");
        }

    }
}
