package com.example.springdatajpa.services.impl;

import com.example.springdatajpa.dtos.CityDTO;
import com.example.springdatajpa.dtos.StateDTO;
import com.example.springdatajpa.models.City;
import com.example.springdatajpa.models.State;
import com.example.springdatajpa.repositories.CityRepository;
import com.example.springdatajpa.services.CityService;
import com.example.springdatajpa.services.StateService;
import com.example.springdatajpa.services.exceptions.ResourceNotFoundException;
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
    public List<CityDTO> findByStateId(Long id) {
        List<City> cities = repository.findByStateId(id);
        if (cities == null || cities.isEmpty()) {
            throw new ResourceNotFoundException("Cidade não encontrada.");
        }
        return cities.stream().map(CityDTO::new).toList();
    }

    @Override
    public void copyDTOToEntity(City city, CityDTO cityDTO) {
        city.setId(cityDTO.getId());
        city.setName(cityDTO.getName());
        State state = new State();
        stateService.copyDTOToEntity(state, cityDTO.getState());
        city.setState(state);
    }
}
