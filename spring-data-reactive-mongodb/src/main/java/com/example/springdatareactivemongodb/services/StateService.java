package com.example.springdatareactivemongodb.services;

import com.example.springdatareactivemongodb.dtos.StateDTO;
import com.example.springdatareactivemongodb.models.State;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StateService {

    Flux<StateDTO> findAll();
    Mono<StateDTO> findById(String id);
    Mono<StateDTO> findByUf(String uf);
    void copyDTOToEntity(State state, StateDTO stateDTO);
}
