package com.example.springdatamongodb.services.impl;

import com.example.springdatamongodb.dtos.PersonDTO;
import com.example.springdatamongodb.models.Address;
import com.example.springdatamongodb.models.Person;
import com.example.springdatamongodb.repositories.PersonRepository;
import com.example.springdatamongodb.services.AddressService;
import com.example.springdatamongodb.services.PersonService;
import com.example.springdatamongodb.services.exceptions.DatabaseException;
import com.example.springdatamongodb.services.exceptions.ResourceNotFoundException;
import org.bson.types.ObjectId;
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
    public PersonDTO findById(String id) {
        try {
            return new PersonDTO(repository.findById(new ObjectId(id))
                    .orElseThrow(() -> new ResourceNotFoundException("Pessoa não encontrada.")));
        } catch (IllegalArgumentException e) {
            throw new DatabaseException("Erro ao converter chave para ObjectId");
        }
    }

    @Override
    public PersonDTO save(PersonDTO personDTO) {
        Person person = new Person();
        copyDTOToEntity(person, personDTO);
        return new PersonDTO(repository.save(person));
    }

    @Override
    public PersonDTO update(String id, PersonDTO personDTO) {
        try {
            Person person = repository.findById(new ObjectId(id)).orElseThrow(() -> new ResourceNotFoundException("Pessoa não encontrada."));
            copyDTOToEntity(person, personDTO);
            return new PersonDTO(repository.save(person));
        } catch (IllegalArgumentException e) {
            throw new DatabaseException("Erro ao converter chave para ObjectId");
        }
    }

    @Override
    public void delete(String id) {
        try {
            repository.deleteById(new ObjectId(id));
        } catch (IllegalArgumentException e) {
            throw new DatabaseException("Erro ao converter chave para ObjectId");
        }
    }

    private void copyDTOToEntity(Person person, PersonDTO personDTO) {
        person.setName(personDTO.getName());
        person.setAge(personDTO.getAge());
        Address address = new Address();
        addressService.copyDTOToEntity(address, personDTO.getAddress());
        person.setAddress(address);
    }

}
