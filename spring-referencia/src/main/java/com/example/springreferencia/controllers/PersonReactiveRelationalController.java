package com.example.springreferencia.controllers;

import com.example.springreferencia.dtos.PersonDTO;
import com.example.springreferencia.services.PersonReactiveService;
import com.example.springreferencia.services.impl.PersonReactiveRelationalServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/reactive-relational")
public class PersonReactiveRelationalController {

    private final PersonReactiveService service;

    public PersonReactiveRelationalController(PersonReactiveRelationalServiceImpl service) {
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

    @GetMapping("/find-by-company/{company}")
    public Flux<PersonDTO> findByCompany(@PathVariable Integer company) {
        return service.findByCompany(company);
    }

}
