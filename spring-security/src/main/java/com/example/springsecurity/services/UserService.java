package com.example.springsecurity.services;

import com.example.springsecurity.dtos.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    Page<UserDTO> findAll(Pageable pageable);
    UserDTO findById(Long id);
    void save(UserDTO userDTO);
    void update(UserDTO userDTO, Long id);
    void delete(Long id);
}
