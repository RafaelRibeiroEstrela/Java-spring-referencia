package com.example.springdataredis.controllers;

import com.example.springdataredis.dtos.PersonDTO;
import com.example.springdataredis.services.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/persons")
public class PersonController {

    private final PersonService service;

    public PersonController(PersonService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<PersonDTO>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonDTO> findById(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<PersonDTO> save(@RequestBody PersonDTO personDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(personDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonDTO> update(@PathVariable String id, @RequestBody PersonDTO personDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(service.update(id, personDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
