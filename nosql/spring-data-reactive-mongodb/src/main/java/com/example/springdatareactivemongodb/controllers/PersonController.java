package com.example.springdatareactivemongodb.controllers;

import com.example.springdatareactivemongodb.dtos.PersonDTO;
import com.example.springdatareactivemongodb.services.PersonService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/persons")
public class PersonController {

    private final PersonService service;

    public PersonController(PersonService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Flux<PersonDTO>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mono<PersonDTO>> findById(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<Mono<PersonDTO>> save(@RequestBody @Valid PersonDTO personDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(personDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Mono<PersonDTO>> update(@PathVariable String id, @RequestBody @Valid PersonDTO personDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(service.update(id, personDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Mono<Void>> delete(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.delete(id));
    }

}
