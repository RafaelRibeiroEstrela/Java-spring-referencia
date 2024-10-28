package com.example.springsecurity.dtos;

import com.example.springsecurity.models.Role;
import jakarta.validation.constraints.NotEmpty;

public class RoleDTO {

    private Long id;

    @NotEmpty(message = "O perfil é obrigatório")
    private String authority;

    public RoleDTO() {}

    public RoleDTO(Role role) {
        this.id = role.getId();
        this.authority = role.getAuthority();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
