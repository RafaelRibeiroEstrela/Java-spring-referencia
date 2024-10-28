package com.example.springdatareactivemongodb.services;

import com.example.springdatareactivemongodb.dtos.AddressDTO;
import com.example.springdatareactivemongodb.models.Address;

public interface AddressService {

    void copyDTOToEntity(Address address, AddressDTO addressDTO);
}
