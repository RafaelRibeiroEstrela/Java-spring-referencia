package com.example.springdatareactivemongodb.controllers;

import com.example.springdatareactivemongodb.dtos.StateDTO;
import com.example.springdatareactivemongodb.services.StateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/states")
public class StateController {

    private final StateService service;

    public StateController(StateService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Flux<StateDTO>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mono<StateDTO>> findById(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
    }

    @GetMapping("/uf/{uf}")
    public ResponseEntity<Mono<StateDTO>> findByUf(@PathVariable String uf) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findByUf(uf));
    }

}
