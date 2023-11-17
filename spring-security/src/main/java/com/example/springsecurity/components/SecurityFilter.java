package com.example.springsecurity.components;

import com.example.springsecurity.repositories.UserRepository;
import com.example.springsecurity.services.exceptions.ResourceNotFoundException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    private final TokenManagment tokenManagment;
    private final UserRepository repository;

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
