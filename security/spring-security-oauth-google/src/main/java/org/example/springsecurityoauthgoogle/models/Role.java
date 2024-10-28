package org.example.springsecurityoauthgoogle.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "roles")
@Getter
@Setter
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String authority; // Pode ser um nome de papel como 'ROLE_ADMIN'

    @Override
    public String getAuthority() {
        return authority;
    }
}
