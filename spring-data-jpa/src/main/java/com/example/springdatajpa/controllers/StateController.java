package com.example.springdatajpa.controllers;

import com.example.springdatajpa.dtos.StateDTO;
import com.example.springdatajpa.services.StateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/states")
public class StateController {

    private final StateService service;

    public StateController(StateService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<StateDTO>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StateDTO> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
    }

    @GetMapping("/uf/{uf}")
    public ResponseEntity<StateDTO> findByUf(@PathVariable String uf) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findByUf(uf));
    }

}
