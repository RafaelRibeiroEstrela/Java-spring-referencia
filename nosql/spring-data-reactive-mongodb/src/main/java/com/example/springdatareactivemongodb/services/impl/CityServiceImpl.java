package com.example.springdatareactivemongodb.services.impl;

import com.example.springdatareactivemongodb.dtos.CityDTO;
import com.example.springdatareactivemongodb.models.City;
import com.example.springdatareactivemongodb.models.State;
import com.example.springdatareactivemongodb.repositories.CityRepository;
import com.example.springdatareactivemongodb.services.CityService;
import com.example.springdatareactivemongodb.services.StateService;
import com.example.springdatareactivemongodb.services.exceptions.DatabaseException;
import com.example.springdatareactivemongodb.services.exceptions.ResourceNotFoundException;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class CityServiceImpl implements CityService {

    private final CityRepository repository;
    private final StateService stateService;

    public CityServiceImpl(CityRepository repository, StateService stateService) {
        this.repository = repository;
        this.stateService = stateService;
    }

    @Override
    public Mono<CityDTO> findByStateId(String id) {
        try {
            return repository.findById(new ObjectId(id))
                    .map(CityDTO::new)
                    .switchIfEmpty(Mono.error(new ResourceNotFoundException("Cidade não encontrada.")));
        } catch (IllegalArgumentException e) {
            throw new DatabaseException("Erro ao converter chave para ObjectId");
        }
    }

    @Override
    public void copyDTOToEntity(City city, CityDTO cityDTO) {
        try {
            city.setId(new ObjectId(cityDTO.getId()));
            city.setName(cityDTO.getName());
            State state = new State();
            stateService.copyDTOToEntity(state, cityDTO.getState());
            city.setState(state);
        } catch (IllegalArgumentException e) {
            throw new DatabaseException("Erro ao converter chave para ObjectId");
        }

    }
}
