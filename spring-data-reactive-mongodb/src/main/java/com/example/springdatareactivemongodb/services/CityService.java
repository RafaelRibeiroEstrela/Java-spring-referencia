package com.example.springdatareactivemongodb.services;

import com.example.springdatareactivemongodb.dtos.CityDTO;
import com.example.springdatareactivemongodb.models.City;
import reactor.core.publisher.Mono;

public interface CityService {

    Mono<CityDTO> findByStateId(String id);
    void copyDTOToEntity(City city, CityDTO cityDTO);
}
