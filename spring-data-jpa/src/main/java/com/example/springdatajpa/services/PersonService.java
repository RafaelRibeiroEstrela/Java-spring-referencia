package com.example.springdatajpa.services;

import com.example.springdatajpa.dtos.PersonDTO;

import java.util.List;

public interface PersonService {

    List<PersonDTO> findAll();
    PersonDTO findById(Long id);
    PersonDTO save(PersonDTO personDTO);
    PersonDTO update(Long id, PersonDTO personDTO);
    void delete(Long id);
}
