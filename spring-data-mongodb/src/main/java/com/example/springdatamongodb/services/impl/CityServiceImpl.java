package com.example.springdatamongodb.services.impl;

import com.example.springdatamongodb.dtos.CityDTO;
import com.example.springdatamongodb.models.City;
import com.example.springdatamongodb.models.State;
import com.example.springdatamongodb.repositories.CityRepository;
import com.example.springdatamongodb.services.CityService;
import com.example.springdatamongodb.services.StateService;
import com.example.springdatamongodb.services.exceptions.DatabaseException;
import com.example.springdatamongodb.services.exceptions.ResourceNotFoundException;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityServiceImpl implements CityService {

    private final CityRepository repository;
    private final StateService stateService;

    public CityServiceImpl(CityRepository repository, StateService stateService) {
        this.repository = repository;
        this.stateService = stateService;
    }

    @Override
    public List<CityDTO> findByStateId(String id) {
        try {
            List<City> cities = repository.findByStateId(new ObjectId(id));
            if (cities == null || cities.isEmpty()) {
                throw new ResourceNotFoundException("Cidade não encontrada.");
            }
            return cities.stream().map(CityDTO::new).toList();
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
