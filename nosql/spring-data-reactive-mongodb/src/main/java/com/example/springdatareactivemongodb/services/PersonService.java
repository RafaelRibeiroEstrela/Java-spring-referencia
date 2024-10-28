package com.example.springdatareactivemongodb.services;

import com.example.springdatareactivemongodb.dtos.PersonDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PersonService {

    Flux<PersonDTO> findAll();
    Mono<PersonDTO> findById(String id);
    Mono<PersonDTO> save(PersonDTO personDTO);
    Mono<PersonDTO> update(String id, PersonDTO personDTO);
    Mono<Void> delete(String id);
}
