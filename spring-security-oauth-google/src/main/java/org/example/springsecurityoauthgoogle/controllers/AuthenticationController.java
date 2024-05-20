package org.example.springsecurityoauthgoogle.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.springsecurityoauthgoogle.components.TokenManagment;
import org.example.springsecurityoauthgoogle.dtos.AuthenticationDTO;
import org.example.springsecurityoauthgoogle.dtos.LoginDTO;
import org.example.springsecurityoauthgoogle.models.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final TokenManagment tokenManagment;

    @PostMapping("/login")
    public ResponseEntity<LoginDTO> login(@RequestBody @Valid AuthenticationDTO authenticationDTO) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(authenticationDTO.getUsername(), authenticationDTO.getPassword());
        Authentication auth = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        String token = tokenManagment.generate((User) auth.getPrincipal());
        return ResponseEntity.status(HttpStatus.CREATED).body(new LoginDTO(token));
    }

}
