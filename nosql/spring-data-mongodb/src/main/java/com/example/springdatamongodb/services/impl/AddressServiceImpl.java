package com.example.springdatamongodb.services.impl;

import com.example.springdatamongodb.dtos.AddressDTO;
import com.example.springdatamongodb.models.Address;
import com.example.springdatamongodb.models.City;
import com.example.springdatamongodb.services.AddressService;
import com.example.springdatamongodb.services.CityService;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {

    private final CityService cityService;

    public AddressServiceImpl(CityService cityService) {
        this.cityService = cityService;
    }

    @Override
    public void copyDTOToEntity(Address address, AddressDTO addressDTO) {
        address.setStreet(addressDTO.getStreet());
        address.setNumber(addressDTO.getNumber());
        address.setDistrict(addressDTO.getDistrict());
        City city = new City();
        cityService.copyDTOToEntity(city, addressDTO.getCity());
        address.setCity(city);
    }
}
