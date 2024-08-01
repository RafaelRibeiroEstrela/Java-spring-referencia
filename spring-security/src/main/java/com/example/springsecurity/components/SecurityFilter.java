package com.example.springsecurity.components;

import com.example.springsecurity.models.EndpointPermission;
import com.example.springsecurity.repositories.UserRepository;
import com.example.springsecurity.services.EndpointPermissionService;
import com.example.springsecurity.services.exceptions.ResourceNotFoundException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    private final TokenManagment tokenManagment;
    private final UserRepository repository;
    private final EndpointPermissionService endpointPermissionService;

    public SecurityFilter(TokenManagment tokenManagment, UserRepository repository) {
        this.tokenManagment = tokenManagment;
        this.repository = repository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = recoverToken(request);
        if (token != null && !token.isEmpty()) {
            String username = tokenManagment.validadeToken(token);
            UserDetails user = repository.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException("Usuario não encontrado"));
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }

        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            String method = request.getMethod();
            String uri = request.getRequestURI();
            Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();

            boolean isAllowed = authorities.stream().anyMatch(authority -> {
                List<EndpointPermission> permissions = endpointPermissionService.getPermissionsForAuthority(authority.getAuthority());
                return permissions.stream().anyMatch(permission -> permission.getMethod().name().equalsIgnoreCase(method) && uri.matches(permission.getUrl()));
            });

            if (!isAllowed) {
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                return;
            }
        }

        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header == null || header.isEmpty()) {
            return null;
        }
        return header.replace("Bearer ", "");
    }
}
