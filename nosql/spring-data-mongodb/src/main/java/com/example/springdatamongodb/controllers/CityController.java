package com.example.springdatamongodb.controllers;

import com.example.springdatamongodb.dtos.CityDTO;
import com.example.springdatamongodb.services.CityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cities")
public class CityController {

    private final CityService service;

    public CityController(CityService service) {
        this.service = service;
    }

    @GetMapping("/state/{id}")
    public ResponseEntity<List<CityDTO>> findByStateId(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findByStateId(id));
    }
}
