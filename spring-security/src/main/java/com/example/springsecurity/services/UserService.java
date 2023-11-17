package com.example.springsecurity.services;

import com.example.springsecurity.dtos.UserDTO;

public interface UserService {

    UserDTO findById(Long id);
    void save(UserDTO userDTO);
    void update(UserDTO userDTO, Long id);
    void delete(Long id);
}
