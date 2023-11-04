package com.example.springdataredis.controllers;

import com.example.springdataredis.dtos.StateDTO;
import com.example.springdataredis.services.StateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<StateDTO> findById(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<StateDTO> save(@RequestBody StateDTO stateDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(stateDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StateDTO> update(@PathVariable String id, @RequestBody StateDTO stateDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(service.update(id, stateDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<StateDTO> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }


}
