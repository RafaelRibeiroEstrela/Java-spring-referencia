package com.example.springdatajpa.services;

import com.example.springdatajpa.dtos.AddressDTO;
import com.example.springdatajpa.models.Address;

public interface AddressService {

    void copyDTOToEntity(Address address, AddressDTO addressDTO);
    AddressDTO findById(Long id);
    AddressDTO save(AddressDTO addressDTO);
    AddressDTO update(Long id, AddressDTO addressDTO);
    void delete(Long id);
}
