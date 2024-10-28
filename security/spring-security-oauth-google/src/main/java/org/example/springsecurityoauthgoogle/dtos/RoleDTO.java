package org.example.springsecurityoauthgoogle.dtos;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.springsecurityoauthgoogle.models.Role;

@Data
@NoArgsConstructor
public class RoleDTO {

    private Long id;

    @NotEmpty(message = "O perfil é obrigatório")
    private String authority;

    public RoleDTO(Role role) {
        this.id = role.getId();
        this.authority = role.getAuthority();
    }
}
