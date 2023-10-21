package com.example.springdatamongodb.services;

import com.example.springdatamongodb.dtos.CityDTO;
import com.example.springdatamongodb.models.City;

import java.util.List;

public interface CityService {

    List<CityDTO> findByStateId(String id);
    void copyDTOToEntity(City city, CityDTO cityDTO);
}
