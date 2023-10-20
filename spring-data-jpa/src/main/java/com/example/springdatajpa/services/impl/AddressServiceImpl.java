package com.example.springdatajpa.services.impl;

import com.example.springdatajpa.dtos.AddressDTO;
import com.example.springdatajpa.models.Address;
import com.example.springdatajpa.models.City;
import com.example.springdatajpa.repositories.AddressRepository;
import com.example.springdatajpa.services.AddressService;
import com.example.springdatajpa.services.CityService;
import com.example.springdatajpa.services.exceptions.DatabaseException;
import com.example.springdatajpa.services.exceptions.ResourceNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository repository;
    private final CityService cityService;

    public AddressServiceImpl(AddressRepository repository, CityService cityService) {
        this.repository = repository;
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

    @Override
    public AddressDTO findById(Long id) {
        return new AddressDTO(repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Endereço não encontrado.")));
    }

    @Override
    public AddressDTO save(AddressDTO addressDTO) {
        Address address = new Address();
        copyDTOToEntity(address, addressDTO);
        return new AddressDTO(repository.save(address));
    }

    @Override
    public AddressDTO update(Long id, AddressDTO addressDTO) {
        Address address = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Endereço não encontrado."));
        copyDTOToEntity(address, addressDTO);
        return new AddressDTO(repository.save(address));
    }

    @Override
    public void delete(Long id) {
        try {
            repository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Endereço não encontrado.");
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Integrity violation");
        }
    }
}
