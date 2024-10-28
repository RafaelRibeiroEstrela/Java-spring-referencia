package com.example.springdatareactivemongodb.controllers;

import com.example.springdatareactivemongodb.dtos.CityDTO;
import com.example.springdatareactivemongodb.services.CityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/cities")
public class CityController {

    private final CityService service;

    public CityController(CityService service) {
        this.service = service;
    }

    @GetMapping("/state/{id}")
    public ResponseEntity<Mono<CityDTO>> findByStateId(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findByStateId(id));
    }
}
