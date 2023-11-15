package com.example.springcloudnetflixeurekaclient2.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/client2")
public class Controller {

    @GetMapping
    public ResponseEntity<String> get() {
        return ResponseEntity.status(HttpStatus.OK).body("Dados do client2!!");
    }
}
