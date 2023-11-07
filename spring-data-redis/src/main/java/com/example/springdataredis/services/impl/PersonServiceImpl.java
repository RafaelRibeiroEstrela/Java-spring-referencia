package com.example.springdataredis.services.impl;

import com.example.springdataredis.caches.PersonCache;
import com.example.springdataredis.dtos.PersonDTO;
import com.example.springdataredis.models.Person;
import com.example.springdataredis.services.PersonService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonCache cache;


    public PersonServiceImpl(PersonCache cache) {
        this.cache = cache;
    }

    @Override
    public List<PersonDTO> findAll() {
        return cache.findAll().stream().map(PersonDTO::new).toList();
    }

    @Override
    public PersonDTO findById(String id) {
        return new PersonDTO(cache.findById(id));
    }

    @Override
    public PersonDTO save(PersonDTO personDTO) {
        Person person = new Person();
        person.setId(UUID.randomUUID().toString());
        copyDTOToEntity(personDTO, person);
        return new PersonDTO(cache.save(person, person.getId()));
    }

    @Override
    public PersonDTO update(String id, PersonDTO personDTO) {
        Person person = cache.findById(id);
        copyDTOToEntity(personDTO, person);
        return new PersonDTO(cache.update(person, person.getId()));
    }

    @Override
    public void delete(String id) {
        cache.delete(id);
    }

    private void copyDTOToEntity(PersonDTO personDTO, Person person) {
        person.setName(personDTO.getName());
        person.setAge(personDTO.getAge());
    }
}
