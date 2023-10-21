package com.example.springdatamongodb.services;

import com.example.springdatamongodb.dtos.StateDTO;
import com.example.springdatamongodb.models.State;

import java.util.List;

public interface StateService {

    List<StateDTO> findAll();
    StateDTO findById(String id);
    StateDTO findByUf(String uf);
    void copyDTOToEntity(State state, StateDTO stateDTO);
}
