package com.example.springdatajpa.services;

import com.example.springdatajpa.dtos.CityDTO;
import com.example.springdatajpa.models.City;

import java.util.List;

public interface CityService {

    List<CityDTO> findByStateId(Long id);
    void copyDTOToEntity(City city, CityDTO cityDTO);
}
