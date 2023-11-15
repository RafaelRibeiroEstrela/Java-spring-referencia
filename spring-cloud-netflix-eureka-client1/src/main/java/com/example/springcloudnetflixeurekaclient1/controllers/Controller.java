package com.example.springcloudnetflixeurekaclient1.controllers;

import com.example.springcloudnetflixeurekaclient1.clients.Client1Request;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/client1")
public class Controller {


    private final Client1Request request;

    public Controller(Client1Request request) {
        this.request = request;
    }

    @GetMapping
    public ResponseEntity<String> get() {
        return ResponseEntity.status(HttpStatus.OK).body(request.getData());
    }
}
