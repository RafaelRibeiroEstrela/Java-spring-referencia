package com.example.springsecurity.dtos;

public class LoginDTO {

    private String token;

    public LoginDTO() {}

    public LoginDTO(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
