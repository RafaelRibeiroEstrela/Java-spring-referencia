package org.example.springsecurityoauthgoogle.dtos;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.springsecurityoauthgoogle.models.User;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
public class UserDTO {

    private Long id;

    @NotEmpty(message = "Username é obrigatório")
    private String username;

    @NotEmpty(message = "Password é obrigatório")
    private String password;

    private Set<RoleDTO> roles = new HashSet<>();

    public UserDTO(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.roles.addAll(user.getRoles().stream()
                .map(RoleDTO::new)
                .toList());
    }
}
