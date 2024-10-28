package com.example.springdatamongodb.services;

import com.example.springdatamongodb.dtos.PersonDTO;

import java.util.List;

public interface PersonService {

    List<PersonDTO> findAll();
    PersonDTO findById(String id);
    PersonDTO save(PersonDTO personDTO);
    PersonDTO update(String id, PersonDTO personDTO);
    void delete(String id);
}
