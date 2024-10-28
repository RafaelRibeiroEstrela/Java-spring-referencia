package com.example.springdatamongodb.services;

import com.example.springdatamongodb.dtos.AddressDTO;
import com.example.springdatamongodb.models.Address;

public interface AddressService {

    void copyDTOToEntity(Address address, AddressDTO addressDTO);
}
