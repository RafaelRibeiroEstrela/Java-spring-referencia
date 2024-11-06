package com.example.service1.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/controller")
public class Controller {

    @GetMapping
    public ResponseEntity<String> get() {
        return ResponseEntity.ok("Service 1");
    }
}
