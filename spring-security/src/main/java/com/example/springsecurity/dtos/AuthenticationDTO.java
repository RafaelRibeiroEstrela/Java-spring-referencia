package com.example.springsecurity.dtos;

import jakarta.validation.constraints.NotEmpty;

public class AuthenticationDTO {

    @NotEmpty(message = "Username é obrigatório")
    private String username;

    @NotEmpty(message = "Password é obrigatório")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
