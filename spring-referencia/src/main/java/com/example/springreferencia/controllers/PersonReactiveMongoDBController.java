package com.example.springreferencia.controllers;

import com.example.springreferencia.dtos.PersonDTO;
import com.example.springreferencia.services.PersonReactiveMongoDBService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/reactive-mongodb")
public class PersonReactiveMongoDBController {

    private final PersonReactiveMongoDBService service;

    public PersonReactiveMongoDBController(PersonReactiveMongoDBService service) {
        this.service = service;
    }

    @GetMapping("/find-all")
    public Flux<PersonDTO> findAll() {
        return service.findAll();
    }

    @GetMapping("/find-by-id/{id}")
    public Mono<PersonDTO> findById(@PathVariable String id) {
        return service.findById(id);
    }

}
