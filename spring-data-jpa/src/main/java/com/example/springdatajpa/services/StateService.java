package com.example.springdatajpa.services;

import com.example.springdatajpa.dtos.StateDTO;
import com.example.springdatajpa.models.State;

import java.util.List;

public interface StateService {

    List<StateDTO> findAll();
    StateDTO findById(Long id);
    StateDTO findByUf(String uf);
    void copyDTOToEntity(State state, StateDTO stateDTO);
}
