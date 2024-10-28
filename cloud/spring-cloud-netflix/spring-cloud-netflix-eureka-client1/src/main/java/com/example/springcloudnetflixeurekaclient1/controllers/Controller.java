package com.example.springcloudnetflixeurekaclient1.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/controller")
public class Controller {

    @GetMapping("/get")
    public ResponseEntity<String> get() {
        return ResponseEntity.status(HttpStatus.OK).body("Service1 OK");
    }
}
