package com.example.springdatajpa.services.impl;

import com.example.springdatajpa.dtos.StateDTO;
import com.example.springdatajpa.models.State;
import com.example.springdatajpa.repositories.StateRepository;
import com.example.springdatajpa.services.StateService;
import com.example.springdatajpa.services.exceptions.ResourceNotFoundException;
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
    public StateDTO findById(Long id) {
        return new StateDTO(repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Estado não encontrado.")));
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
        state.setId(stateDTO.getId());
        state.setName(stateDTO.getName());
        state.setUf(stateDTO.getUf());
    }
}
