package com.example.springsecurity.controllers;

import com.example.springsecurity.components.TokenManagment;
import com.example.springsecurity.dtos.AuthenticationDTO;
import com.example.springsecurity.dtos.LoginDTO;
import com.example.springsecurity.models.User;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final TokenManagment tokenManagment;

    public AuthenticationController(AuthenticationManager authenticationManager, TokenManagment tokenManagment) {
        this.authenticationManager = authenticationManager;
        this.tokenManagment = tokenManagment;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginDTO> login(@RequestBody @Valid AuthenticationDTO authenticationDTO) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(authenticationDTO.getUsername(), authenticationDTO.getPassword());
        Authentication auth = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        String token = tokenManagment.generate((User) auth.getPrincipal());
        return ResponseEntity.status(HttpStatus.CREATED).body(new LoginDTO(token));
    }

}
