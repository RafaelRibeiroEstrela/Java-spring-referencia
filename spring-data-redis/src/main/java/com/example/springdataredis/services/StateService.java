package com.example.springdataredis.services;

import com.example.springdataredis.dtos.StateDTO;

import java.util.List;

public interface StateService {

    List<StateDTO> findAll();
    StateDTO findById(String id);
    StateDTO save(StateDTO stateDTO);
    StateDTO update(String id, StateDTO stateDTO);
    void delete(String id);
}
