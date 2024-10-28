package com.example.springdatareactivemongodb.services.impl;

import com.example.springdatareactivemongodb.dtos.PersonDTO;
import com.example.springdatareactivemongodb.models.Address;
import com.example.springdatareactivemongodb.models.Person;
import com.example.springdatareactivemongodb.repositories.PersonRepository;
import com.example.springdatareactivemongodb.services.AddressService;
import com.example.springdatareactivemongodb.services.PersonService;
import com.example.springdatareactivemongodb.services.exceptions.DatabaseException;
import com.example.springdatareactivemongodb.services.exceptions.ResourceNotFoundException;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository repository;
    private final AddressService addressService;

    public PersonServiceImpl(PersonRepository repository, AddressService addressService) {
        this.repository = repository;
        this.addressService = addressService;
    }

    @Override
    public Flux<PersonDTO> findAll() {
        return repository.findAll()
                .map(PersonDTO::new)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Pessoa não encontrada.")));
    }

    @Override
    public Mono<PersonDTO> findById(String id) {
        try {
            return repository.findById(new ObjectId(id))
                    .map(PersonDTO::new)
                    .switchIfEmpty(Mono.error(new ResourceNotFoundException("Pessoa não encontrada.")));
        } catch (IllegalArgumentException e) {
            throw new DatabaseException("Erro ao converter chave para ObjectId");
        }
    }

    @Override
    public Mono<PersonDTO> save(PersonDTO personDTO) {
        Person person = new Person();
        copyDTOToEntity(person, personDTO);
        return repository.save(person)
                .map(PersonDTO::new);
    }

    @Override
    public Mono<PersonDTO> update(String id, PersonDTO personDTO) {
        try {
            return repository.findById(new ObjectId(id)).flatMap(obj -> {
                copyDTOToEntity(obj, personDTO);
                return repository.save(obj)
                        .map(PersonDTO::new);
            }).switchIfEmpty(Mono.error(new ResourceNotFoundException("Pessoa não encontrada.")));
        } catch (IllegalArgumentException e) {
            throw new DatabaseException("Erro ao converter chave para ObjectId");
        }
    }

    @Override
    public Mono<Void> delete(String id) {
        try {
            return repository.deleteById(new ObjectId(id))
                    .switchIfEmpty(Mono.error(new ResourceNotFoundException("Pessoa não encontrada.")));
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
