package com.example.springdatamongodb.services.impl;

import com.example.springdatamongodb.dtos.StateDTO;
import com.example.springdatamongodb.models.State;
import com.example.springdatamongodb.repositories.StateRepository;
import com.example.springdatamongodb.services.StateService;
import com.example.springdatamongodb.services.exceptions.DatabaseException;
import com.example.springdatamongodb.services.exceptions.ResourceNotFoundException;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StateServiceImpl implements StateService {

    private final StateRepository repository;

    public StateServiceImpl(StateRepository repository) {
        this.repository = repository;
    }


    @Override
    public List<StateDTO> findAll() {
        List<State> states = repository.findAll();
        if (states == null || states.isEmpty()) {
            throw new ResourceNotFoundException("Estado não encontrado.");
        }
        return states.stream().map(StateDTO::new).toList();
    }

    @Override
    public StateDTO findById(String id) {
        try {
            return new StateDTO(repository.findById(new ObjectId(id)).orElseThrow(() -> new ResourceNotFoundException("Estado não encontrado.")));
        } catch (IllegalArgumentException e) {
            throw new DatabaseException("Erro ao converter chave para ObjectId");
        }

    }

    @Override
    public StateDTO findByUf(String uf) {
        State state = repository.findByUf(uf);
        if (state == null) {
            throw new ResourceNotFoundException("Estado não encontrado.");
        }
        return new StateDTO(state);
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
