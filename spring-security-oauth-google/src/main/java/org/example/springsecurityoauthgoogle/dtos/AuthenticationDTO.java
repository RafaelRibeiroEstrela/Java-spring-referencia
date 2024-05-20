package org.example.springsecurityoauthgoogle.dtos;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class AuthenticationDTO {

    @NotEmpty(message = "Username é obrigatório")
    private String username;

    @NotEmpty(message = "Password é obrigatório")
    private String password;
}
