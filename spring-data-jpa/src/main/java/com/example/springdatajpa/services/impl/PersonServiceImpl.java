package com.example.springdatajpa.services.impl;

import com.example.springdatajpa.dtos.AddressDTO;
import com.example.springdatajpa.dtos.PersonDTO;
import com.example.springdatajpa.models.Address;
import com.example.springdatajpa.models.Person;
import com.example.springdatajpa.repositories.PersonRepository;
import com.example.springdatajpa.services.AddressService;
import com.example.springdatajpa.services.PersonService;
import com.example.springdatajpa.services.exceptions.DatabaseException;
import com.example.springdatajpa.services.exceptions.ResourceNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository repository;
    private final AddressService addressService;

    public PersonServiceImpl(PersonRepository repository, AddressService addressService) {
        this.repository = repository;
        this.addressService = addressService;
    }

    @Override
    public List<PersonDTO> findAll() {
        List<Person> persons = repository.findAll();
        if (persons == null || persons.isEmpty()) {
            throw new ResourceNotFoundException("Pessoas não encontradas.");
        }
        return persons.stream()
                .map(PersonDTO::new)
                .toList();
    }

    @Override
    public PersonDTO findById(Long id) {
        return new PersonDTO(repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pessoa não encontrada.")));
    }

    @Override
    public PersonDTO save(PersonDTO personDTO) {
        Person person = new Person();
        copyDTOToEntity(person, personDTO);
        if (personDTO.getAddress().getId() == null) {
            AddressDTO addressDTO = addressService.save(personDTO.getAddress());
            Address address = new Address();
            addressService.copyDTOToEntity(address, addressDTO);
            person.setAddress(address);
        }
        return new PersonDTO(repository.save(person));
    }

    @Override
    public PersonDTO update(Long id, PersonDTO personDTO) {
        Person person = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Pessoa não encontrada."));
        copyDTOToEntity(person, personDTO);
        AddressDTO addressDTO = addressService.update(personDTO.getAddress().getId(), personDTO.getAddress());
        Address address = new Address();
        addressService.copyDTOToEntity(address, addressDTO);
        person.setAddress(address);
        return new PersonDTO(repository.save(person));
    }

    @Override
    public void delete(Long id) {
        try {
            repository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Cidade não encontrada.");
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Integrity violation");
        }
    }

    private void copyDTOToEntity(Person person, PersonDTO personDTO) {
        person.setName(personDTO.getName());
        person.setAge(personDTO.getAge());
    }

}
