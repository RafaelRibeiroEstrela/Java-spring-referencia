package com.example.springreferencia.services;

import com.example.springreferencia.dtos.PersonDTO;

import java.util.List;

public interface PersonService {

    List<PersonDTO> findAll();
    PersonDTO findById(String id);
}
