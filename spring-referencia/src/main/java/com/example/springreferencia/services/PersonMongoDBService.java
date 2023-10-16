package com.example.springreferencia.services;

import com.example.springreferencia.dtos.PersonDTO;

import java.util.List;

public interface PersonMongoDBService {

    List<PersonDTO> findAll();
    PersonDTO findById(String id);
}
