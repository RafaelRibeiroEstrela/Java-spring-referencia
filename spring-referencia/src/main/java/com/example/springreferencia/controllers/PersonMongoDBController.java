package com.example.springreferencia.controllers;

import com.example.springreferencia.dtos.PersonDTO;
import com.example.springreferencia.services.PersonMongoDBService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/mongodb")
public class PersonMongoDBController {

    private final PersonMongoDBService service;

    public PersonMongoDBController(PersonMongoDBService service) {
        this.service = service;
    }

    @GetMapping("/find-all")
    public List<PersonDTO> findAll() {
        return service.findAll();
    }

    @GetMapping("/find-by-id/{id}")
    public PersonDTO findById(@PathVariable String id) {
        return service.findById(id);
    }

}
