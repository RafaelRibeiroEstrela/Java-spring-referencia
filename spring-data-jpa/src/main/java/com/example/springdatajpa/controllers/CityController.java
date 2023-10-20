package com.example.springdatajpa.controllers;

import com.example.springdatajpa.dtos.CityDTO;
import com.example.springdatajpa.services.CityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cities")
public class CityController {

    private final CityService service;

    public CityController(CityService service) {
        this.service = service;
    }

    @GetMapping("/state/{id}")
    public ResponseEntity<List<CityDTO>> findByStateId(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findByStateId(id));
    }
}
