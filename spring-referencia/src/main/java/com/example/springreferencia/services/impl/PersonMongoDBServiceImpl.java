package com.example.springreferencia.services.impl;

import com.example.springreferencia.dtos.PersonDTO;
import com.example.springreferencia.models.mongodb.PersonMongoDB;
import com.example.springreferencia.repositories.PersonMongoDBRepository;
import com.example.springreferencia.services.PersonMongoDBService;
import com.example.springreferencia.services.exceptions.InvalidRuleException;
import com.example.springreferencia.services.exceptions.ResourceNotFoundException;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonMongoDBServiceImpl implements PersonMongoDBService {

    private final PersonMongoDBRepository repository;
    private static final Logger LOGGER = LoggerFactory.getLogger(PersonMongoDBServiceImpl.class);

    public PersonMongoDBServiceImpl(PersonMongoDBRepository repository) {
        this.repository = repository;
    }


    @Override
    public List<PersonDTO> findAll() {
        LOGGER.info("Buscando todos os recursos no Mongodb");
        List<PersonMongoDB> personMongoDBList = repository.findAll();
        if (personMongoDBList == null || personMongoDBList.isEmpty()) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
        return personMongoDBList.stream()
                .map(PersonDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public PersonDTO findById(String id) {
        LOGGER.info("Buscando recurso no Mongodb com chave = {}", id);
        try {
            ObjectId objectId = new ObjectId(id);
            Optional<PersonMongoDB> personMongoDBOptional = repository.findById(objectId);
            if (personMongoDBOptional.isEmpty()) {
                LOGGER.error("Não foi encontrado um recurso com a chave = {}", id);
                throw new ResourceNotFoundException("Não foi encontrado um recurso com a chave = " + id);
            }
            return new PersonDTO(personMongoDBOptional.get());
        } catch (IllegalArgumentException e) {
            LOGGER.error("Erro: {}", e.getMessage());
            throw new InvalidRuleException(e.getMessage());
        }
    }
}
