package org.example.springsecurityoauthgoogle.services.impl;

import org.example.springsecurityoauthgoogle.dtos.UserDTO;
import org.example.springsecurityoauthgoogle.models.User;
import org.example.springsecurityoauthgoogle.repositories.UserRepository;
import org.example.springsecurityoauthgoogle.services.UserService;
import org.example.springsecurityoauthgoogle.services.exceptions.ApiException;
import org.example.springsecurityoauthgoogle.services.exceptions.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserDetailsService, UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + username));
    }

    @Override
    public Page<UserDTO> findAll(Pageable pageable) {
        Page<User> page = repository.findAll(pageable);
        if (page.isEmpty()) {
            throw new ResourceNotFoundException("Usuário não encontrado");
        }
        return page.map(UserDTO::new);
    }

    @Override
    public UserDTO findById(Long id) {
        return new UserDTO(repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado")));
    }

    @Override
    public void save(UserDTO userDTO) {
        if (userDTO.getRoles().isEmpty()) {
            throw new ApiException("O perfil de usuário é obrigatório");
        }
        if (repository.findByUsername(userDTO.getUsername()).isPresent()) {
            throw new ApiException("Já existe um usuário com username = " + userDTO.getUsername());
        }
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        User user = new User();
        copyDTOToEntity(userDTO, user);
        repository.save(user);
    }

    @Override
    public void update(UserDTO userDTO, Long id) {
        if (userDTO.getRoles().isEmpty()) {
            throw new ApiException("O perfil de usuário é obrigatório");
        }
        User user = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));
        if (!userDTO.getUsername().equals(user.getUsername())) {
            if (repository.findByUsername(userDTO.getUsername()).isPresent()) {
                throw new ApiException("Já existe um usuário com username = " + userDTO.getUsername());
            }
        }
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        copyDTOToEntity(userDTO, user);
        repository.save(user);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    private void copyDTOToEntity(UserDTO userDTO, User user) {
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
    }
}
