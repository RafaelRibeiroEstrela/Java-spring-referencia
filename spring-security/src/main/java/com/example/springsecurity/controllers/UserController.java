package com.example.springsecurity.controllers;

import com.example.springsecurity.dtos.UserDTO;
import com.example.springsecurity.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody @Valid UserDTO userDTO) {
        service.save(userDTO);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
