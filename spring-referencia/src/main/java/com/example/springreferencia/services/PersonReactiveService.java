package com.example.springreferencia.services;

import com.example.springreferencia.dtos.PersonDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PersonReactiveService {

    Flux<PersonDTO> findAll();
    Mono<PersonDTO> findById(String id);
    Flux<PersonDTO> findByCompany(Integer company);
}
